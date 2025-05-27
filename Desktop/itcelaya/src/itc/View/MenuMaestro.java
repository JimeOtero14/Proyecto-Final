package itc.View;

import itc.View.RegistrarCalificaciones;
import itc.Model.Maestro;
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

public class MenuMaestro {
    public void mostrarMenuMaestro(Maestro maestro) {
        Stage stage = new Stage();

        //Commit
        ImageView imagenLogoITC = new ImageView("itc/View/Imagenes/BannerTecno.jpg"); // sin setFitWidth
        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtSDBDA = new Text("Bienvenid@ maestr@ "+maestro.getNombre());

        txtITC.setFont(Font.font("Montserrat", FontWeight.BOLD, 30));
        txtITC.setFill(Color.WHITE);
        txtSDBDA.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        txtSDBDA.setFill(Color.WHITE);

        HBox HBimagenLogo = new HBox(imagenLogoITC);
        HBimagenLogo.setAlignment(Pos.CENTER);

        HBox HBITC = new HBox(txtITC);
        HBITC.setAlignment(Pos.CENTER);

        HBox HBLSDBDA = new HBox(txtSDBDA);
        HBLSDBDA.setAlignment(Pos.CENTER);

        // Credencial
        VBox credencial = crearCredencialMaestro(maestro);

        // Botones
        Button btnRegistrar = new Button("Registrar calificaciones");
        Button btnEditar = new Button("Editar calificaciones");
        Button btnConsultar = new Button("Consultar lista de alumnos");
        
        btnRegistrar.setOnAction(e -> {
    RegistrarCalificaciones vistaRegistrar = new RegistrarCalificaciones(maestro);
    vistaRegistrar.mostrar();
});
        
        btnEditar.setOnAction(e -> {
    itc.View.EditarCalificaciones editarView = new itc.View.EditarCalificaciones(maestro);
    editarView.mostrar();
});

        btnConsultar.setOnAction(e -> {
    ConsultarAlumnos view = new ConsultarAlumnos(maestro);
    view.mostrar();
});



        // Estilo de los botones
        Font fuenteBoton = Font.font("Arial", FontWeight.BOLD, 14);
        Button[] botones = {btnRegistrar, btnEditar, btnConsultar};

        for (Button b : botones) {
            b.setFont(fuenteBoton);
            b.setPrefWidth(200);
            b.setPrefHeight(40);
            b.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        }

        HBox hboxBotones = new HBox(20, btnRegistrar, btnEditar, btnConsultar);
        hboxBotones.setAlignment(Pos.CENTER);
        hboxBotones.setPadding(new Insets(20, 0, 0, 0));

        // Panel principal
        VBox panelMain = new VBox(20, HBimagenLogo, HBITC, HBLSDBDA, credencial, hboxBotones);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));

        StackPane root = new StackPane(panelMain);
        root.setStyle("-fx-background-color: DARKGREEN;");
        Scene scene = new Scene(root, 900, 700);

        stage.setScene(scene);
        stage.setTitle("Menú Maestro - " + maestro.getNombreCompleto());
        stage.show();
    }

    private VBox crearCredencialMaestro(Maestro maestro) {
        String estiloCredencial = "-fx-background-color: white; " +
                "-fx-background-radius: 15; " +
                "-fx-border-color: #2c3e50; " +
                "-fx-border-radius: 15; " +
                "-fx-border-width: 3; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0.5, 0, 4);";

        Label lblTitulo = new Label("CREDENCIAL DE MAESTRO");
        lblTitulo.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        lblTitulo.setTextFill(Color.DARKBLUE);

        Label lblNombre = new Label("Nombre: " + maestro.getNombreCompleto());
        Label lblClave = new Label("Clave: " + maestro.getCveMaestro());
        Label lblCarrera = new Label("Carrera: " + maestro.getCarrera());

        Font fontDatos = Font.font("Arial", FontWeight.NORMAL, 16);
        lblNombre.setFont(fontDatos);
        lblClave.setFont(fontDatos);
        lblCarrera.setFont(fontDatos);

        HBox panelFotoDatos = new HBox(20, new VBox(10, lblNombre, lblClave, lblCarrera));
        panelFotoDatos.setAlignment(Pos.CENTER_LEFT);

        VBox credencial = new VBox(20, lblTitulo, panelFotoDatos);
        credencial.setStyle(estiloCredencial);
        credencial.setPadding(new Insets(20));
        credencial.setAlignment(Pos.CENTER);
        credencial.setMaxWidth(600);

        return credencial;
    }
}
