package itc.Controller;

import itc.Model.Alumno;
import itc.Model.Calificacion;
import itc.Model.ConsultarAlumnosModel;
import itc.Model.Strategy.OrdenamientoStrategy;
import itc.Model.Strategy.OrdenarPorNombre;
import itc.Model.Strategy.OrdenarPorNoControl;
import itc.Model.KardexModel;
import itc.View.ConsultarAlumnos;
import itc.Model.Maestro;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAlumnosController {

    private ConsultarAlumnos view;
    private ConsultarAlumnosModel model;
    private Maestro maestro;
    private OrdenamientoStrategy estrategia;
    private KardexModel kardexModel = new KardexModel();

    private double promedioActual;
    private int ultimoIdMateria;
    private int ultimoIdGrupo;
    private String ultimaClaveMaestro;
    private int ultimoNoControl;

    public ConsultarAlumnosController(ConsultarAlumnos view, Maestro maestro) {
        this.view = view;
        this.maestro = maestro;
        this.model = new ConsultarAlumnosModel();
        this.estrategia = new OrdenarPorNombre();

        cargarMaterias();

        view.comboMateria.setOnAction(e -> cargarGrupos());
        view.comboGrupo.setOnAction(e -> cargarAlumnos());
        view.listaAlumnos.setOnMouseClicked(e -> {
            String seleccionado = view.listaAlumnos.getSelectionModel().getSelectedItem();
            if (seleccionado != null) cargarCalificaciones(seleccionado);
        });
        view.comboOrdenamiento.setOnAction(e -> {
            String opcion = view.comboOrdenamiento.getSelectionModel().getSelectedItem();
            estrategia = opcion.equals("Por nombre") ? new OrdenarPorNombre() : new OrdenarPorNoControl();
            cargarAlumnos();
        });

        view.btnKardex.setOnAction(e -> {
            boolean yaExiste = kardexModel.yaRegistradoEnKardex(ultimoNoControl, ultimoIdGrupo, ultimaClaveMaestro, ultimoIdMateria);
            if (yaExiste) {
                mostrarAlerta("La calificación final ya está registrada en Kardex.");
            } else {
                boolean exito = kardexModel.registrarEnKardex(ultimoNoControl, ultimoIdGrupo, ultimaClaveMaestro, ultimoIdMateria, promedioActual);
                if (exito) {
                    mostrarAlerta("Registro en Kardex exitoso.");
                    view.btnKardex.setVisible(false);
                } else {
                    mostrarAlerta("Error al registrar en Kardex.");
                }
            }
        });

        view.btnCerrar.setOnAction(e -> view.getStage().close());
    }

    private void cargarMaterias() {
        List<String> materias = model.obtenerMateriasPorMaestro(maestro.getCveMaestro());
        Platform.runLater(() -> view.comboMateria.setItems(FXCollections.observableArrayList(materias)));
    }

    private void cargarGrupos() {
        String materia = view.comboMateria.getSelectionModel().getSelectedItem();
        if (materia != null) {
            List<String> grupos = model.obtenerGruposPorMateriaYMaestro(materia, maestro.getCveMaestro());
            Platform.runLater(() -> view.comboGrupo.setItems(FXCollections.observableArrayList(grupos)));
        }
    }

    private void cargarAlumnos() {
        String grupoStr = view.comboGrupo.getSelectionModel().getSelectedItem();
        if (grupoStr != null) {
            int idGrupo = Integer.parseInt(grupoStr);
            List<String> datosCrudos = model.obtenerAlumnosPorGrupo(idGrupo);
            List<Alumno> alumnos = new ArrayList<>();
            for (String entrada : datosCrudos) {
                String[] partes = entrada.split(" - ");
                if (partes.length == 2) {
                    String noControl = partes[0].trim();
                    String[] nombreCompleto = partes[1].trim().split(" ", 2);
                    String nombre = nombreCompleto[0];
                    String apellido = nombreCompleto.length > 1 ? nombreCompleto[1] : "";
                    alumnos.add(new Alumno(nombre, apellido, "", "", noControl, null));
                }
            }
            List<Alumno> ordenados = estrategia.ordenar(alumnos);
            List<String> nombres = new ArrayList<>();
            for (Alumno a : ordenados) {
                nombres.add(a.getNoControl() + " - " + a.getNombreCompleto());
            }
            Platform.runLater(() -> view.listaAlumnos.setItems(FXCollections.observableArrayList(nombres)));
        }
    }

    private void cargarCalificaciones(String entrada) {
        String[] partes = entrada.split(" - ");
        if (partes.length == 2) {
            int noControl = Integer.parseInt(partes[0].trim());
            String materia = view.comboMateria.getSelectionModel().getSelectedItem();
            String grupoStr = view.comboGrupo.getSelectionModel().getSelectedItem();
            int idGrupo = Integer.parseInt(grupoStr);

            List<Calificacion> calificaciones = model.obtenerCalificaciones(noControl, idGrupo, maestro.getCveMaestro(), materia);
            Platform.runLater(() -> {
                view.tablaCalificaciones.setItems(FXCollections.observableArrayList(calificaciones));

                if (calificaciones.size() == 4) {
                    promedioActual = calificaciones.stream().mapToInt(Calificacion::getCalificacion).average().orElse(0);
                    String estado = promedioActual >= 70 ? "Aprobado" : "Reprobado";
                    view.txtPromedio.setText("Promedio: " + String.format("%.2f", promedioActual));
                    view.txtEstado.setText("Estado: " + estado);
                    view.btnKardex.setVisible(true);

                    ultimoNoControl = noControl;
                    ultimoIdGrupo = idGrupo;
                    ultimaClaveMaestro = maestro.getCveMaestro();
                    ultimoIdMateria = model.obtenerIdMateriaPorNombre(materia);
                } else {
                    view.txtPromedio.setText("Promedio: --");
                    view.txtEstado.setText("Estado: --");
                    view.btnKardex.setVisible(false);
                }
            });
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Kardex");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
