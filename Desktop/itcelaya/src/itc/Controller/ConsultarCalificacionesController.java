package itc.Controller;

import itc.Model.Alumno;
import itc.Model.ConsultarCalificacionesModel;
import itc.Model.ConsultarCalificacionesModel.CalificacionDetalle;
import itc.View.ConsultarCalificaciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.List;

public class ConsultarCalificacionesController {

    private ConsultarCalificaciones view;
    private ConsultarCalificacionesModel model;
    private Alumno alumno;

    public ConsultarCalificacionesController(ConsultarCalificaciones view, Alumno alumno) {
        this.view = view;
        this.alumno = alumno;
        this.model = new ConsultarCalificacionesModel();

        cargarCalificaciones();

        view.btnCerrar.setOnAction(e -> view.getStage().close());
    }

    private void cargarCalificaciones() {
        try {
            int noControl = Integer.parseInt(alumno.getNoControl());
            List<CalificacionDetalle> lista = model.obtenerCalificacionesAlumno(noControl);

            Platform.runLater(() -> {
                view.tablaCalificaciones.setItems(FXCollections.observableArrayList(lista));
            });
        } catch (NumberFormatException e) {
            System.err.println("Error: noControl no es un número válido. Detalles: " + e.getMessage());
        }
    }
}
