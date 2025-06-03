package itc.View;

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

import java.io.ByteArrayInputStream;

public class MenuMaestro {

    private Maestro maestro;
    private Stage stage;

    public MenuMaestro() {}

    public void mostrarMenuMaestro(Maestro maestro) {
        this.maestro = maestro;
        this.stage = new Stage();

        ImageView imagenLogoITC = new ImageView(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg")));
        imagenLogoITC.setPreserveRatio(true);
        imagenLogoITC.setFitWidth(1000);

        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtBienvenida = new Text("Bienvenid@ maestro(a) " + maestro.getNombre() + " " + maestro.getPrimerApellido() + " " + maestro.getSegundoApellido());

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

        VBox credencial = crearCredencialMaestro();

        Button btnRegistrar = new Button("Registrar calificaciones");
        Button btnEditar = new Button("Editar calificaciones");
        Button btnConsultar = new Button("Consultar alumnos");

        btnRegistrar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnEditar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnConsultar.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        btnRegistrar.setPrefWidth(220);
        btnEditar.setPrefWidth(220);
        btnConsultar.setPrefWidth(220);

        btnRegistrar.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        btnEditar.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        btnConsultar.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");

        btnRegistrar.setOnAction(e -> new RegistrarCalificaciones(maestro).mostrar());
        btnEditar.setOnAction(e -> new EditarCalificaciones(maestro).mostrar());
        btnConsultar.setOnAction(e -> new ConsultarAlumnos(maestro).mostrar());

        VBox botones = new VBox(15, btnRegistrar, btnEditar, btnConsultar);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(20));

        VBox panelMain = new VBox(10, hbCerrarSesion, hbLogo, hbITC, hbBienvenida, credencial, botones);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        StackPane root = new StackPane(panelMain);
        Scene scene = new Scene(root, 900, 700);

        stage.setScene(scene);
        stage.setTitle("Menú Maestro");
        stage.setMaximized(true);
        stage.show();
    }

    private VBox crearCredencialMaestro() {
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
        Label lblClave = new Label("Clave de Maestro: " + maestro.getCveMaestro());
        Label lblCarrera = new Label("Carrera: " + maestro.getCarrera());

        Font fontDatos = Font.font("Arial", FontWeight.NORMAL, 16);
        lblNombre.setFont(fontDatos);
        lblClave.setFont(fontDatos);
        lblCarrera.setFont(fontDatos);

        ImageView imgMaestro = new ImageView();
        if (maestro.getFoto() != null) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(maestro.getFoto());
                Image image = new Image(bis);
                imgMaestro.setImage(image);
                imgMaestro.setFitWidth(120);
                imgMaestro.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("Error cargando la foto del maestro: " + e.getMessage());
            }
        } else {
            imgMaestro.setImage(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/sin_foto.png")));
        }

        VBox datos = new VBox(10, lblNombre, lblClave, lblCarrera);
        VBox credencial = new VBox(20, lblTitulo, imgMaestro, datos);
        credencial.setStyle(estiloCredencial);
        credencial.setPadding(new Insets(20));
        credencial.setAlignment(Pos.CENTER);
        credencial.setMaxWidth(600);

        return credencial;
    }
}
