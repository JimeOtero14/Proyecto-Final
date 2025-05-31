package itc.Controller;

import itc.Model.Calificacion;
import itc.Model.ConsultarAlumnosModel;
import itc.Model.Maestro;
import itc.View.ConsultarAlumnos;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.List;

public class ConsultarAlumnosController {
    private ConsultarAlumnos view;
    private ConsultarAlumnosModel model;
    private Maestro maestro;

    public ConsultarAlumnosController(ConsultarAlumnos view, Maestro maestro) {
        this.view = view;
        this.maestro = maestro;
        this.model = new ConsultarAlumnosModel();

        cargarMaterias();

        view.comboMateria.setOnAction(e -> cargarGrupos());
        view.comboGrupo.setOnAction(e -> cargarAlumnos());
        view.listaAlumnos.setOnMouseClicked(e -> cargarCalificaciones());
        view.btnCerrar.setOnAction(e -> view.getStage().close());
    }

    private void cargarMaterias() {
        List<String> materias = model.obtenerMateriasPorMaestro(maestro.getCveMaestro());
        Platform.runLater(() -> {
            view.comboMateria.setItems(FXCollections.observableArrayList(materias));
            view.comboGrupo.getItems().clear();
            view.listaAlumnos.getItems().clear();
            view.tablaCalificaciones.getItems().clear();
        });
    }

    private void cargarGrupos() {
        String materia = view.comboMateria.getValue();
        if (materia != null) {
            List<String> grupos = model.obtenerGruposPorMateriaYMaestro(materia, maestro.getCveMaestro());
            Platform.runLater(() -> {
                view.comboGrupo.setItems(FXCollections.observableArrayList(grupos));
                view.listaAlumnos.getItems().clear();
                view.tablaCalificaciones.getItems().clear();
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
                    view.listaAlumnos.setItems(FXCollections.observableArrayList(alumnos));
                    view.tablaCalificaciones.getItems().clear();
                });
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void cargarCalificaciones() {
        String alumno = view.listaAlumnos.getSelectionModel().getSelectedItem();
        String grupoStr = view.comboGrupo.getValue();
        String materia = view.comboMateria.getValue();

        if (alumno != null && grupoStr != null && materia != null) {
            try {
                int idGrupo = Integer.parseInt(grupoStr);
                int noControl = Integer.parseInt(alumno.split(" - ")[0]);
                List<Calificacion> calificaciones = model.obtenerCalificaciones(noControl, idGrupo, maestro.getCveMaestro(), materia);

                Platform.runLater(() -> {
                    view.tablaCalificaciones.setItems(FXCollections.observableArrayList(calificaciones));
                });
            } catch (NumberFormatException ignored) {
            }
        }
    }
}
