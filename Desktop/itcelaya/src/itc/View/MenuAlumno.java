package itc.View;

import itc.Model.Alumno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;

public class MenuAlumno {

    private Alumno alumno;
    private Stage stage;

    public MenuAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.stage = new Stage();
    }

    public void mostrarMenuAlumno() {
        ImageView imagenLogoITC = new ImageView(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg")));
        imagenLogoITC.setPreserveRatio(true);
        imagenLogoITC.setFitWidth(300);

        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtBienvenida = new Text("Bienvenid@ alumno(a) " + alumno.getNombre() + " " + alumno.getPrimerApellido() + " " + alumno.getSegundoApellido());

        txtITC.setFont(Font.font("Montserrat", FontWeight.BOLD, 30));
        txtITC.setFill(Color.WHITE);

        txtBienvenida.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        txtBienvenida.setFill(Color.WHITE);

        Button btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        btnCerrarSesion.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        btnCerrarSesion.setOnAction(e -> stage.close());

        HBox hbCerrarSesion = new HBox(btnCerrarSesion);
        hbCerrarSesion.setAlignment(Pos.TOP_RIGHT);

        HBox hbLogo = new HBox(imagenLogoITC);
        hbLogo.setAlignment(Pos.CENTER);

        HBox hbITC = new HBox(txtITC);
        hbITC.setAlignment(Pos.CENTER);

        HBox hbBienvenida = new HBox(txtBienvenida);
        hbBienvenida.setAlignment(Pos.CENTER);

        VBox credencial = crearCredencialAlumno();

        Button btnConsultarCalif = new Button("Consultar calificaciones");
        btnConsultarCalif.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnConsultarCalif.setPrefWidth(200);
        btnConsultarCalif.setPrefHeight(40);
        btnConsultarCalif.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        btnConsultarCalif.setOnAction(e -> {
            ConsultarCalificaciones view = new ConsultarCalificaciones(alumno);
            view.mostrar();
        });

        HBox hboxBotones = new HBox(20, btnConsultarCalif);
        hboxBotones.setAlignment(Pos.CENTER);
        hboxBotones.setPadding(new Insets(20, 0, 0, 0));

        VBox panelMain = new VBox(10, hbCerrarSesion, hbLogo, hbITC, hbBienvenida, credencial, hboxBotones);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        StackPane root = new StackPane(panelMain);
        Scene scene = new Scene(root, 900, 700);

        stage.setScene(scene);
        stage.setTitle("Menú Alumno");
        stage.setMaximized(true);
        stage.show();
    }

    private VBox crearCredencialAlumno() {
        String estiloCredencial = "-fx-background-color: white; " +
                "-fx-background-radius: 15; " +
                "-fx-border-color: #2c3e50; " +
                "-fx-border-radius: 15; " +
                "-fx-border-width: 3; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0.5, 0, 4);";

        Label lblTitulo = new Label("CREDENCIAL DE ALUMNO");
        lblTitulo.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        lblTitulo.setTextFill(Color.DARKBLUE);

        Label lblNombre = new Label("Nombre: " + alumno.getNombreCompleto());
        Label lblNoControl = new Label("No. Control: " + alumno.getNoControl());
        Label lblCarrera = new Label("Carrera: " + alumno.getCarrera());

        Font fontDatos = Font.font("Arial", FontWeight.NORMAL, 16);
        lblNombre.setFont(fontDatos);
        lblNoControl.setFont(fontDatos);
        lblCarrera.setFont(fontDatos);

        ImageView imgAlumno = new ImageView();
        if (alumno.getFoto() != null) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(alumno.getFoto());
                Image image = new Image(bis);
                imgAlumno.setImage(image);
                imgAlumno.setFitWidth(120);
                imgAlumno.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("Error cargando la foto desde byte[]: " + e.getMessage());
            }
        } else {
            imgAlumno.setImage(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/sin_foto.png")));
        }

        VBox datos = new VBox(10, lblNombre, lblNoControl, lblCarrera);
        VBox credencial = new VBox(20, imgAlumno, lblTitulo, datos);
        credencial.setStyle(estiloCredencial);
        credencial.setPadding(new Insets(20));
        credencial.setAlignment(Pos.CENTER);
        credencial.setMaxWidth(600);

        return credencial;
    }
}
