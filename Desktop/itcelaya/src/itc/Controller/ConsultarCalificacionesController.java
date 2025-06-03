package itc.Controller;

import itc.Model.Alumno;
import itc.Model.ConsultarCalificacionesModel;
import itc.Model.ConsultarCalificacionesModel.CalificacionDetalle;
import itc.View.ConsultarCalificaciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

                Map<String, List<CalificacionDetalle>> agrupadoPorMateria =
                        lista.stream().collect(Collectors.groupingBy(CalificacionDetalle::getNombreMateria));

                StringBuilder resumen = new StringBuilder("Resumen: ");
                for (Map.Entry<String, List<CalificacionDetalle>> entry : agrupadoPorMateria.entrySet()) {
                    List<CalificacionDetalle> parciales = entry.getValue();
                    long total = parciales.stream().map(CalificacionDetalle::getParcial).distinct().count();
                    if (total == 4) {
                        double promedio = parciales.stream().mapToInt(CalificacionDetalle::getCalificacion).average().orElse(0.0);
                        String estado = promedio >= 70 ? "Aprobado" : "Reprobado";
                        resumen.append(entry.getKey())
                               .append(" -> Promedio: ").append(String.format("%.2f", promedio))
                               .append(" | Estado: ").append(estado).append("\n");
                    }
                }

                view.txtResumen.setText(resumen.toString());
            });
        } catch (NumberFormatException e) {
            System.err.println("Error: noControl no es un número válido. Detalles: " + e.getMessage());
        }
    }
}
