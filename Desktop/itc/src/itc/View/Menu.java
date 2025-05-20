package itc.View;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
    public void mostrarMENU() {
        Stage stageMenu = new Stage();
        
        StackPane root = new StackPane();
        root.setBackground(null);
        
        Scene display = new Scene(root,1200, 900, Color.DARKGREEN);
       
        stageMenu.setScene(display);
        stageMenu.setTitle("Sistema de base de alumnos - MENU");
        stageMenu.setResizable(false);
        stageMenu.show();
    }
}
