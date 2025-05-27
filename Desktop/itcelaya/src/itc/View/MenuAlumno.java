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

public class MenuAlumno {
//commit
    private Alumno alumno;
    private Stage stage;

    public MenuAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.stage = new Stage();
    }

    public void mostrarMenuAlumno() {
        // Encabezado
        ImageView imagenLogoITC = new ImageView(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg")));
        imagenLogoITC.setPreserveRatio(true);
        imagenLogoITC.setFitWidth(300);

        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtBienvenida = new Text("Bienvenid@ alumn@ " + alumno.getNombre());

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

        // Credencial alumno
        VBox credencial = crearCredencialAlumno();

        // Botones
        Button btnConsultarCalif = new Button("Consultar calificaciones");
        Button btnBoletaCalif = new Button("Boleta de calificaciones");
        
        btnConsultarCalif.setOnAction(e -> {
    ConsultarCalificaciones view = new ConsultarCalificaciones(alumno);
    view.mostrar();
});


        Font fuenteBoton = Font.font("Arial", FontWeight.BOLD, 14);
        Button[] botones = {btnConsultarCalif, btnBoletaCalif};

        for (Button b : botones) {
            b.setFont(fuenteBoton);
            b.setPrefWidth(200);
            b.setPrefHeight(40);
            b.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        }

        HBox hboxBotones = new HBox(20, btnConsultarCalif, btnBoletaCalif);
        hboxBotones.setAlignment(Pos.CENTER);
        hboxBotones.setPadding(new Insets(20, 0, 0, 0));

        // Panel principal
        VBox panelMain = new VBox(20, hbLogo, hbITC, hbBienvenida, credencial, hboxBotones);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        StackPane root = new StackPane(panelMain);
        Scene scene = new Scene(root, 900, 700);

        stage.setScene(scene);
        stage.setTitle("Menú Alumno - " + alumno.getNombreCompleto());
        stage.show();

        // Manejadores de botones
        btnConsultarCalif.setOnAction(e -> abrirConsultarCalificaciones());
        btnBoletaCalif.setOnAction(e -> abrirBoletaCalificaciones());
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

        VBox datos = new VBox(10, lblNombre, lblNoControl, lblCarrera);

        VBox credencial = new VBox(20, lblTitulo, datos);
        credencial.setStyle(estiloCredencial);
        credencial.setPadding(new Insets(20));
        credencial.setAlignment(Pos.CENTER);
        credencial.setMaxWidth(600);

        return credencial;
    }

    // Método para abrir ventana Consultar Calificaciones (debes implementar esta vista y controlador)
    private void abrirConsultarCalificaciones() {
    ConsultarCalificaciones view = new ConsultarCalificaciones(alumno);
    view.mostrar();
}

    

    // Método para abrir ventana Boleta de Calificaciones (debes implementar esta vista y controlador)
    private void abrirBoletaCalificaciones() {
        System.out.println("Abriendo Boleta de Calificaciones...");

        // Ejemplo:
        // BoletaCalificacionesView view = new BoletaCalificacionesView(alumno);
        // view.mostrar();

        // Por ahora solo un mensaje
        // Reemplaza con la apertura real de la ventana
    }
}
