package itc.View;

import itc.Controller.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) {
        ImageView imagenLogoITC = new ImageView("itc/View/Imagenes/BannerTecno.jpg");
        Text txtITC = new Text("Instituto Tecnologico de Celaya");
        Text txtSDBDA = new Text("Sistema de gestion de calificaciones");
        Text txtgridTitle = new Text("Bienvenido");
        Label userName = new Label("Correo:");
        TextField userTextField = new TextField();
        Label pw = new Label("Contraseña:");
        PasswordField pwBox = new PasswordField();
        Button btnLogin = new Button("Iniciar sesion");
        GridPane grid = new GridPane();
        
        txtITC.setFont(Font.font("Montserrat", FontWeight.BOLD, 50));
        txtITC.setFill(Color.WHITE);
        
        txtSDBDA.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        txtSDBDA.setFill(Color.WHITE);
        
        HBox HBimagenLogo = new HBox();
        HBimagenLogo.setAlignment(Pos.CENTER);
        HBimagenLogo.getChildren().add(imagenLogoITC); 
        
        HBox HBITC = new HBox();
        HBITC.setAlignment(Pos.CENTER);
        HBITC.getChildren().add(txtITC); 
        
        HBox HBLSDBDA = new HBox();
        HBLSDBDA.setAlignment(Pos.CENTER);
        HBLSDBDA.getChildren().add(txtSDBDA);
        
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER);
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.setPrefSize(350, 350);
        grid.setMaxSize(350, 350);
        grid.setStyle("-fx-background-color: white; -fx-background-radius: 15; "
            + "-fx-border-color: #ccc; -fx-border-radius: 15; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 4);");
        txtgridTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(txtgridTitle, 0, 0, 2, 1);
        grid.add(userName, 0, 1);
        grid.add(userTextField, 1, 1);
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);
        grid.add(btnLogin,1,4);
        card.getChildren().add(grid);

        
        VBox panelMain = new VBox(15);
        panelMain.setAlignment(Pos.TOP_CENTER);
        panelMain.setPadding(new Insets(15,15,15,15));
        panelMain.getChildren().addAll(HBimagenLogo, HBITC, HBLSDBDA, card);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(panelMain);
        root.setBackground(null);
        
        Scene display = new Scene(root,900,900,Color.DARKGREEN);
        
        primaryStage.setScene(display);
        primaryStage.setTitle("Sistema de gestion de calificaciones - LOGIN");
        primaryStage.setMaximized(true);

        primaryStage.show();

        LoginController obj = new LoginController();
        obj.LoginMenu(btnLogin, userTextField, pwBox);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}