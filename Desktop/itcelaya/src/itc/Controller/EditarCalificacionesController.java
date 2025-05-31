package itc.Controller;

import itc.Model.EditarCalificacionesModel;
import itc.Model.Maestro;
import itc.View.EditarCalificaciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.List;

public class EditarCalificacionesController {

    private EditarCalificaciones view;
    private EditarCalificacionesModel model;
    private Maestro maestro;

    public EditarCalificacionesController(EditarCalificaciones view, Maestro maestro) {
        this.view = view;
        this.maestro = maestro;
        this.model = new EditarCalificacionesModel();

        cargarMaterias();

        view.comboMateria.setOnAction(e -> cargarGrupos());
        view.comboGrupo.setOnAction(e -> cargarAlumnos());
        view.comboAlumno.setOnAction(e -> cargarParciales());

        view.comboParcial.setOnAction(e -> cargarCalificacion());

        view.btnGuardar.setOnAction(e -> guardarCalificacion());
    }

    private void cargarMaterias() {
        List<String> materias = model.obtenerMateriasPorMaestro(maestro.getCveMaestro());
        Platform.runLater(() -> {
            view.comboMateria.setItems(FXCollections.observableArrayList(materias));
            view.comboGrupo.getItems().clear();
            view.comboAlumno.getItems().clear();
            view.comboParcial.getItems().clear();
            view.txtCalificacion.clear();
        });
    }

    private void cargarGrupos() {
        String materia = view.comboMateria.getValue();
        if (materia != null) {
            List<String> grupos = model.obtenerGruposPorMateriaYMaestro(materia, maestro.getCveMaestro());
            Platform.runLater(() -> {
                view.comboGrupo.setItems(FXCollections.observableArrayList(grupos));
                view.comboAlumno.getItems().clear();
                view.comboParcial.getItems().clear();
                view.txtCalificacion.clear();
            });
        }
    }

    private void cargarAlumnos() {
        String grupoStr = view.comboGrupo.getValue();
        if (grupoStr != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                List<String> alumnos = model.obtenerAlumnosPorGrupo(idGrupo);
                Platform.runLater(() -> {
                    view.comboAlumno.setItems(FXCollections.observableArrayList(alumnos));
                    view.comboParcial.getItems().clear();
                    view.txtCalificacion.clear();
                });
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void cargarParciales() {
        String alumno = view.comboAlumno.getValue();
        String materia = view.comboMateria.getValue();
        String grupoStr = view.comboGrupo.getValue();

        if (alumno != null && materia != null && grupoStr != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                int noControl = Integer.parseInt(alumno.split(" - ")[0]);
                List<Integer> parciales = model.obtenerParcialesRegistrados(noControl, idGrupo, maestro.getCveMaestro(), materia);

                Platform.runLater(() -> {
                    view.comboParcial.getItems().clear();
                    for (int i = 1; i <= 4; i++) {
                        view.comboParcial.getItems().add(i);
                    }
                });
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void cargarCalificacion() {
        String alumno = view.comboAlumno.getValue();
        String materia = view.comboMateria.getValue();
        String grupoStr = view.comboGrupo.getValue();
        Integer parcial = view.comboParcial.getValue();

        if (alumno != null && materia != null && grupoStr != null && parcial != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                int noControl = Integer.parseInt(alumno.split(" - ")[0]);
                Integer calif = model.obtenerCalificacion(noControl, idGrupo, maestro.getCveMaestro(), materia, parcial);

                Platform.runLater(() -> {
                    if (calif != null) {
                        view.txtCalificacion.setText(String.valueOf(calif));
                    } else {
                        view.txtCalificacion.clear();
                    }
                });
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void guardarCalificacion() {
        String alumno = view.comboAlumno.getValue();
        String materia = view.comboMateria.getValue();
        String grupoStr = view.comboGrupo.getValue();
        Integer parcial = view.comboParcial.getValue();
        String califStr = view.txtCalificacion.getText();

        if (alumno == null || materia == null || grupoStr == null || parcial == null || califStr.isEmpty()) {
            mostrarAlerta("Error", "Complete todos los campos");
            return;
        }

        try {
            int idGrupo = Integer.parseInt(grupoStr);
            int noControl = Integer.parseInt(alumno.split(" - ")[0]);
            int calificacion = Integer.parseInt(califStr);

            if (calificacion < 0 || calificacion > 100) {
                mostrarAlerta("Error", "Calificación debe estar entre 0 y 100");
                return;
            }

            boolean exito = model.registrarCalificacion(noControl, idGrupo, maestro.getCveMaestro(), materia, parcial, calificacion);

            if (exito) {
                mostrarAlerta("Éxito", "Calificación guardada correctamente");
            } else {
                mostrarAlerta("Error", "No se pudo guardar la calificación");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese un número válido en la calificación");
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
