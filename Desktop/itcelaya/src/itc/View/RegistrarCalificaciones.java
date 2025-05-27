package itc.View;

import itc.Controller.RegistrarCalificacionesController;
import itc.Model.Maestro;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//commit
public class RegistrarCalificaciones {
    private Stage stage;
    private Maestro maestro;

    public ComboBox<String> comboMateria;
    public ComboBox<String> comboGrupo;
    public ComboBox<String> comboAlumno;
    public ComboBox<Integer> comboParcial;
    public TextField txtCalificacion;
    public Button btnRegistrar;

    public RegistrarCalificaciones(Maestro maestro) {
        this.maestro = maestro;
        this.stage = new Stage();
    }

    public void mostrar() {
        stage.setTitle("Registrar Calificaciones");

        ImageView imagenLogoITC = new ImageView(new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg")));
        Text txtITC = new Text("Instituto Tecnológico de Celaya");
        Text txtBienvenida = new Text("Bienvenid@ maestr@ " + maestro.getNombre());

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

        Label lblMateria = new Label("Materia:");
        Label lblGrupo = new Label("Grupo:");
        Label lblAlumno = new Label("Alumno:");
        Label lblParcial = new Label("Parcial:");
        Label lblCalificacion = new Label("Calificación:");

        comboMateria = new ComboBox<>();
        comboGrupo = new ComboBox<>();
        comboAlumno = new ComboBox<>();
        comboParcial = new ComboBox<>();
        txtCalificacion = new TextField();
        btnRegistrar = new Button("Registrar calificación");

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14);
        lblMateria.setFont(labelFont);
        lblGrupo.setFont(labelFont);
        lblAlumno.setFont(labelFont);
        lblParcial.setFont(labelFont);
        lblCalificacion.setFont(labelFont);

        btnRegistrar.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnRegistrar.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 10;");
        btnRegistrar.setPrefWidth(220);
        btnRegistrar.setPrefHeight(40);

        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        grid.add(lblMateria, 0, 0);
        grid.add(comboMateria, 1, 0);
        grid.add(lblGrupo, 0, 1);
        grid.add(comboGrupo, 1, 1);
        grid.add(lblAlumno, 0, 2);
        grid.add(comboAlumno, 1, 2);
        grid.add(lblParcial, 0, 3);
        grid.add(comboParcial, 1, 3);
        grid.add(lblCalificacion, 0, 4);
        grid.add(txtCalificacion, 1, 4);
        grid.add(btnRegistrar, 1, 5);

        VBox panelMain = new VBox(20, hbLogo, hbITC, hbBienvenida, grid);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        Scene scene = new Scene(panelMain, 900, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        new RegistrarCalificacionesController(this, maestro);
    }

    public Stage getStage() {
        return stage;
    }
}