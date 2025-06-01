package itc.View;

import itc.Model.Calificacion;
import itc.Model.Maestro;
import itc.Controller.ConsultarAlumnosController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsultarAlumnos {
    private Stage stage;
    private Maestro maestro;

    public ComboBox<String> comboMateria;
    public ComboBox<String> comboGrupo;
    public ComboBox<String> comboOrdenamiento;
    public ListView<String> listaAlumnos;
    public TableView<Calificacion> tablaCalificaciones;
    public Button btnCerrar;

    public ConsultarAlumnos(Maestro maestro) {
        this.maestro = maestro;
        this.stage = new Stage();
    }

    public void mostrar() {
        stage.setTitle("Consultar Lista de Alumnos");

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
        Label lblAlumnos = new Label("Lista de Alumnos:");
        Label lblCalificaciones = new Label("Calificaciones:");
        Label lblOrdenamiento = new Label("Ordenar por:");

        comboMateria = new ComboBox<>();
        comboGrupo = new ComboBox<>();
        comboOrdenamiento = new ComboBox<>();
        comboOrdenamiento.getItems().addAll("Por nombre", "Por número de control");
        comboOrdenamiento.getSelectionModel().selectFirst(); // opción por defecto

        listaAlumnos = new ListView<>();

        tablaCalificaciones = new TableView<>();
        TableColumn<Calificacion, Integer> colParcial = new TableColumn<>("Parcial");
        TableColumn<Calificacion, Integer> colCalificacion = new TableColumn<>("Calificación");

        colParcial.setCellValueFactory(new PropertyValueFactory<>("parcial"));
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        tablaCalificaciones.getColumns().addAll(colParcial, colCalificacion);
        tablaCalificaciones.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnCerrar.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 10;");
        btnCerrar.setPrefWidth(100);
        btnCerrar.setPrefHeight(35);

        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        grid.add(lblMateria, 0, 0);
        grid.add(comboMateria, 1, 0);
        grid.add(lblGrupo, 0, 1);
        grid.add(comboGrupo, 1, 1);
        grid.add(lblOrdenamiento, 0, 2);
        grid.add(comboOrdenamiento, 1, 2);
        grid.add(lblAlumnos, 0, 3);
        grid.add(listaAlumnos, 1, 3);
        grid.add(lblCalificaciones, 0, 4);
        grid.add(tablaCalificaciones, 1, 4);
        grid.add(btnCerrar, 1, 5);

        listaAlumnos.setPrefHeight(150);
        listaAlumnos.setPrefWidth(300);
        tablaCalificaciones.setPrefHeight(200);
        tablaCalificaciones.setPrefWidth(300);

        VBox panelMain = new VBox(20, hbLogo, hbITC, hbBienvenida, grid);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(20));
        panelMain.setStyle("-fx-background-color: DARKGREEN;");

        Scene scene = new Scene(panelMain, 900, 700);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        new ConsultarAlumnosController(this, maestro);
    }

    public Stage getStage() {
        return stage;
    }
}
