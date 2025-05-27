package itc.Controller;

import itc.Model.Maestro;
import itc.Model.RegistrarCalificacionesModel;
import itc.View.RegistrarCalificaciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
//commit
public class RegistrarCalificacionesController {

    private RegistrarCalificaciones view;
    private RegistrarCalificacionesModel model;
    private Maestro maestro;

    public RegistrarCalificacionesController(RegistrarCalificaciones view, Maestro maestro) {
        this.view = view;
        this.maestro = maestro;
        this.model = new RegistrarCalificacionesModel();

        cargarMaterias();

        // Listeners para llenar combos en cascada
        view.comboMateria.setOnAction(e -> {
            cargarGrupos();
            limpiarParciales();
        });

        view.comboGrupo.setOnAction(e -> {
            cargarAlumnos();
            limpiarParciales();
        });

        view.comboAlumno.setOnAction(e -> cargarParcialesDisponibles());

        // Registrar calificación al hacer clic
        view.btnRegistrar.setOnAction(e -> registrarCalificacion());
    }

    private void cargarMaterias() {
        List<String> materias = model.obtenerMateriasPorMaestro(maestro.getCveMaestro());
        Platform.runLater(() -> {
            view.comboMateria.setItems(FXCollections.observableArrayList(materias));
            view.comboGrupo.getItems().clear();
            view.comboAlumno.getItems().clear();
            limpiarParciales();
        });
    }

    private void cargarGrupos() {
        String materia = view.comboMateria.getValue();
        if (materia != null) {
            List<String> grupos = model.obtenerGruposPorMateriaYMaestro(materia, maestro.getCveMaestro());
            Platform.runLater(() -> {
                view.comboGrupo.setItems(FXCollections.observableArrayList(grupos));
                view.comboAlumno.getItems().clear();
                limpiarParciales();
            });
        }
    }

    private void cargarAlumnos() {
        String grupoStr = view.comboGrupo.getValue();
        if (grupoStr != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                List<String> alumnos = model.obtenerAlumnosPorGrupo(idGrupo);
                Platform.runLater(() -> view.comboAlumno.setItems(FXCollections.observableArrayList(alumnos)));
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void limpiarParciales() {
        Platform.runLater(() -> {
            view.comboParcial.getItems().clear();
            view.txtCalificacion.clear();
        });
    }

    private void cargarParcialesDisponibles() {
        String alumno = view.comboAlumno.getValue();
        String materia = view.comboMateria.getValue();
        String grupoStr = view.comboGrupo.getValue();

        if (alumno != null && materia != null && grupoStr != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                int noControl = Integer.parseInt(alumno.split(" - ")[0]);

                // Parciales ya registrados
                List<Integer> parcialesRegistrados = model.obtenerParcialesRegistrados(noControl, idGrupo, maestro.getCveMaestro(), materia);

                // Todos los parciales posibles: 1 a 4
                List<Integer> parcialesDisponibles = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    if (!parcialesRegistrados.contains(i)) {
                        parcialesDisponibles.add(i);
                    }
                }

                Platform.runLater(() -> {
                    view.comboParcial.getItems().clear();
                    view.comboParcial.getItems().addAll(parcialesDisponibles);
                    if (!parcialesDisponibles.isEmpty()) {
                        view.comboParcial.getSelectionModel().selectFirst();
                    }
                });

            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void registrarCalificacion() {
        String alumnoSel = view.comboAlumno.getValue();
        String materiaSel = view.comboMateria.getValue();
        String grupoSel = view.comboGrupo.getValue();
        Integer parcialSel = view.comboParcial.getValue();
        String califStr = view.txtCalificacion.getText();

        if (alumnoSel == null || materiaSel == null || grupoSel == null || parcialSel == null || califStr.isEmpty()) {
            mostrarAlerta("Error", "Por favor complete todos los campos");
            return;
        }

        try {
            int parcial = parcialSel;
            int calificacion = Integer.parseInt(califStr);
            if (parcial < 1 || parcial > 4) {
                mostrarAlerta("Error", "El parcial debe ser entre 1 y 4");
                return;
            }
            if (calificacion < 0 || calificacion > 100) {
                mostrarAlerta("Error", "La calificación debe ser entre 0 y 100");
                return;
            }

            int noControl = Integer.parseInt(alumnoSel.split(" - ")[0]);
            int idGrupo = Integer.parseInt(grupoSel);

            boolean exito = model.registrarCalificacion(noControl, idGrupo, maestro.getCveMaestro(), materiaSel, parcial, calificacion);

            if (exito) {
                mostrarAlerta("Éxito", "Calificación registrada correctamente");
                cargarParcialesDisponibles(); // actualizar parciales disponibles
                view.txtCalificacion.clear();
            } else {
                mostrarAlerta("Error", "No se pudo registrar la calificación");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Parcial y calificación deben ser números válidos");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
