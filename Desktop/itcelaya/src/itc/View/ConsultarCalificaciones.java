package itc.View;

import itc.Model.ConsultarCalificacionesModel.CalificacionDetalle;
import itc.Model.Alumno;
import itc.Controller.ConsultarCalificacionesController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsultarCalificaciones {
    //commit
    private Stage stage;
    private Alumno alumno;

    public TableView<CalificacionDetalle> tablaCalificaciones;
    public Button btnCerrar;

    public ConsultarCalificaciones(Alumno alumno) {
        this.alumno = alumno;
        this.stage = new Stage();
    }

    public void mostrar() {
        stage.setTitle("Calificaciones de " + alumno.getNombreCompleto());

        ImageView imagenLogoITC = new ImageView(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg")));
        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtBienvenida = new Text("Calificaciones de " + alumno.getNombre());

        txtITC.setFont(Font.font("Montserrat", FontWeight.BOLD, 30));
        txtITC.setFill(Color.WHITE);

        txtBienvenida.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        txtBienvenida.setFill(Color.WHITE);

        HBox hbLogo = new HBox(imagenLogoITC);
        hbLogo.setAlignment(Pos.CENTER);

        HBox hbITC = new HBox(txtITC);
        hbITC.setAlignment(Pos.CENTER);

        HBox hbBienvenida = new HBox(txtBienvenida);
        hbBienvenida.setAlignment(Pos.CENTER);

        tablaCalificaciones = new TableView<>();

        TableColumn<CalificacionDetalle, String> colMateria = new TableColumn<>("Materia");
        TableColumn<CalificacionDetalle, String> colMaestro = new TableColumn<>("Clave Maestro");
        TableColumn<CalificacionDetalle, Integer> colGrupo = new TableColumn<>("Grupo");
        TableColumn<CalificacionDetalle, Integer> colParcial = new TableColumn<>("Parcial");
        TableColumn<CalificacionDetalle, Integer> colCalificacion = new TableColumn<>("Calificación");

        colMateria.setCellValueFactory(new PropertyValueFactory<>("nombreMateria"));
        colMaestro.setCellValueFactory(new PropertyValueFactory<>("cveMaestro"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("idGrupo"));
        colParcial.setCellValueFactory(new PropertyValueFactory<>("parcial"));
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        tablaCalificaciones.getColumns().addAll(colMateria, colMaestro, colGrupo, colParcial, colCalificacion);
        tablaCalificaciones.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaCalificaciones.setPrefHeight(400);

        btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnCerrar.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 10;");
        btnCerrar.setPrefWidth(100);
        btnCerrar.setPrefHeight(35);

        VBox panelMain = new VBox(20, hbLogo, hbITC, hbBienvenida, tablaCalificaciones, btnCerrar);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        Scene scene = new Scene(panelMain, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        new ConsultarCalificacionesController(this, alumno);
    }

    public Stage getStage() {
        return stage;
    }
}
