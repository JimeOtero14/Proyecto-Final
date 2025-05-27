package itc.Controller;

import itc.Model.Alumno;
import itc.Model.AlumnoModel;
import itc.Model.Maestro;
import itc.Model.MaestroModel;
import itc.Model.UsuarioModel;
import itc.Utils.SHA1Encryptor;
import itc.View.MenuAlumno;
import itc.View.MenuMaestro;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//commit
public class btnCambiodeVentana {
    public void LoginMenu(Button btnLogin, TextField userTextField, PasswordField pwBox) {
        btnLogin.setOnAction(event -> {
            if (userTextField.getText().isEmpty() || pwBox.getText().isEmpty()) {
                mostrarAlertaError("Error", "Por favor ingrese usuario y contraseña");
                return;
            }

            String username = userTextField.getText().trim();
            String password = SHA1Encryptor.encrypt(pwBox.getText().trim());

            try {
                UsuarioModel usuarioModel = new UsuarioModel();
                boolean loginExitoso = usuarioModel.validarUsuario(username, password);

                if (loginExitoso) {
                    String rol = usuarioModel.obtenerRolUsuario(username);
                    Stage currentStage = (Stage) btnLogin.getScene().getWindow();

                    if (rol.equals("alumno")) {
                        AlumnoModel alumnoModel = new AlumnoModel();
                        Alumno alumno = alumnoModel.obtenerDatosAlumno(username);
                        if (alumno != null) {
                            MenuAlumno menuAlumno = new MenuAlumno(alumno);
                            menuAlumno.mostrarMenuAlumno();
                            currentStage.close();
                        } else {
                            mostrarAlertaError("Error", "No se encontraron datos para el alumno");
                        }
                    } else if (rol.equals("maestro")) {
                        MaestroModel maestroModel = new MaestroModel();
                        Maestro maestro = maestroModel.obtenerDatosMaestro(username);
                        if (maestro != null) {
                            MenuMaestro menuMaestro = new MenuMaestro();
                            menuMaestro.mostrarMenuMaestro(maestro);
                            currentStage.close();
                        } else {
                            mostrarAlertaError("Error", "No se encontraron datos para el maestro");
                        }
                    } else {
                        mostrarAlertaError("Error", "Rol de usuario no reconocido");
                    }
                } else {
                    mostrarAlertaError("Error de Login", "Usuario o contraseña incorrectos");
                }
            } catch (Exception e) {
                mostrarAlertaError("Error", "Ocurrió un error al iniciar sesión: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
