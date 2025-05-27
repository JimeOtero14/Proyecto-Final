package itc.View;

import itc.Controller.EditarCalificacionesController;
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

public class EditarCalificaciones {
    private Stage stage;
    private Maestro maestro;
    //commit
    public ComboBox<String> comboMateria;
    public ComboBox<String> comboGrupo;
    public ComboBox<String> comboAlumno;
    public ComboBox<Integer> comboParcial;
    public TextField txtCalificacion;
    public Button btnGuardar;

    public EditarCalificaciones(Maestro maestro) {
        this.maestro = maestro;
        this.stage = new Stage();
    }

    public void mostrar() {
        stage.setTitle("Editar Calificaciones");

        // Encabezado
        ImageView imagenLogoITC = new ImageView(
    new Image(getClass().getResourceAsStream("/itc/View/Imagenes/BannerTecno.jpg"))
);
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

        // Etiquetas y controles
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
        btnGuardar = new Button("Guardar cambios");

        // Estilo etiquetas y botones
        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14);
        lblMateria.setFont(labelFont);
        lblGrupo.setFont(labelFont);
        lblAlumno.setFont(labelFont);
        lblParcial.setFont(labelFont);
        lblCalificacion.setFont(labelFont);

        btnGuardar.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnGuardar.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 10;");
        btnGuardar.setPrefWidth(220);
        btnGuardar.setPrefHeight(40);

        // Layout con GridPane
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
        grid.add(btnGuardar, 1, 5);

        VBox panelMain = new VBox(20, hbLogo, hbITC, hbBienvenida, grid);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        Scene scene = new Scene(panelMain, 900, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        new EditarCalificacionesController(this, maestro);
    }
    
    

    public Stage getStage() {
        return stage;
    }
}
