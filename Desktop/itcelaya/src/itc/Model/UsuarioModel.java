package itc.Model;
//0000
import itc.Model.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioModel {
    public boolean validarUsuario(String correo, String contrasenaEncriptada) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasenaEncriptada);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}