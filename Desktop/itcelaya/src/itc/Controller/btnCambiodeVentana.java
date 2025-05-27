package itc.Controller;
//0000
import itc.View.Menu;
import itc.Model.UsuarioModel;
import itc.Utils.SHA1Encryptor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class btnCambiodeVentana {
    public void LoginMenu(Button btnLogin, TextField userTextField, PasswordField pwBox) {
        btnLogin.setOnAction(event -> {
            String username = userTextField.getText();
            String password = SHA1Encryptor.encrypt(pwBox.getText());
            
            UsuarioModel usuarioModel = new UsuarioModel();
            boolean loginExitoso = usuarioModel.validarUsuario(username, password);
            
            if (loginExitoso) {
                Menu sdbdaMenu = new Menu();
                sdbdaMenu.mostrarMENU();
                ((Stage) btnLogin.getScene().getWindow()).close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Login");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseña incorrectos");
                alert.showAndWait();
            }
        });
    }
}