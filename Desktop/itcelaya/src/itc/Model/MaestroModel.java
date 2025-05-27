package itc.Model;

import itc.Model.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//commit
public class MaestroModel {
    public Maestro obtenerDatosMaestro(String correo) {
        String sql = "SELECT m.*, c.nombre as nombre_carrera " +
                     "FROM maestro m " +
                     "JOIN carrera c ON m.id_carrera = c.id_carrera " +
                     "JOIN usuario u ON m.id_usuario = u.id_usuario " +
                     "WHERE u.correo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Maestro(
                    rs.getString("cveMaestro"),
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("nombre_carrera")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new Maestro("", "No encontrado", "", "", "");
    }
}