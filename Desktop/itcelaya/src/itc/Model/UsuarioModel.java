package itc.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioModel {

    public boolean validarUsuario(String correo, String contrasena) {
        String query = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo.trim());
            stmt.setString(2, contrasena.trim());

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public String obtenerTipoUsuario(String correo, String contrasena) {
        String tipoUsuario = null;
        String query = "SELECT tipoUsuario FROM usuario WHERE correo = ? AND contrasena = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo.trim());
            stmt.setString(2, contrasena.trim());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipoUsuario = rs.getString("tipoUsuario");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return tipoUsuario;
    }
}
