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

public class LoginController {

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
                    String tipoUsuario = usuarioModel.obtenerTipoUsuario(username, password);
                    
                    System.out.println("Correo: " + username);
                    System.out.println("Contraseña en SHA1: " + password);
                    System.out.println("Tipo de usuario obtenido: " + tipoUsuario);

                    if (tipoUsuario == null) {
                        mostrarAlertaError("Error", "No se pudo obtener el tipo de usuario.");
                        return;
                    }

                    Stage currentStage = null;
                    if (btnLogin.getScene() != null && btnLogin.getScene().getWindow() != null) {
                        currentStage = (Stage) btnLogin.getScene().getWindow();
                    }

                    if ("Alumno".equalsIgnoreCase(tipoUsuario)) {
                        AlumnoModel alumnoModel = new AlumnoModel();
                        Alumno alumno = alumnoModel.obtenerDatosAlumno(username);
                        if (alumno != null) {
                            MenuAlumno menuAlumno = new MenuAlumno(alumno);
                            menuAlumno.mostrarMenuAlumno();
                            if (currentStage != null) currentStage.close();
                        } else {
                            mostrarAlertaError("Error", "No se encontraron datos para el alumno");
                        }

                    } else if ("Maestro".equalsIgnoreCase(tipoUsuario)) {
                        MaestroModel maestroModel = new MaestroModel();
                        Maestro maestro = maestroModel.obtenerDatosMaestro(username);
                        if (maestro != null) {
                            MenuMaestro menuMaestro = new MenuMaestro();
                            menuMaestro.mostrarMenuMaestro(maestro);
                            if (currentStage != null) currentStage.close();
                        } else {
                            mostrarAlertaError("Error", "No se encontraron datos para el maestro");
                        }

                    } else {
                        mostrarAlertaError("Error", "Rol de usuario no reconocido: " + tipoUsuario);
                    }

                } else {
                    mostrarAlertaError("Error de Login", "Usuario o contraseña incorrectos");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlertaError("Error", "Ocurrió un error al iniciar sesión.\nConsulta la consola para más detalles.");
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
