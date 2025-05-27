package itc.Controller;

import itc.Model.Alumno;
import itc.Model.ConsultarCalificacionesModel;
import itc.Model.ConsultarCalificacionesModel.CalificacionDetalle;
import itc.View.ConsultarCalificaciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.List;
//commit
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
        List<CalificacionDetalle> lista = model.obtenerCalificacionesAlumno(alumno.getNoControl());

        Platform.runLater(() -> {
            view.tablaCalificaciones.setItems(FXCollections.observableArrayList(lista));
        });
    }
}
