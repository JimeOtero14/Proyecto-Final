package itc.Controller;

import itc.View.Menu;
import javafx.scene.control.Button;

public class btnCambiodeVentana {

    public void LoginMenu(Button btnLogin) {
        btnLogin.setOnAction(event -> {
            Menu sdbdaMenu = new Menu();
            sdbdaMenu.mostrarMENU();
        });
    }
}
