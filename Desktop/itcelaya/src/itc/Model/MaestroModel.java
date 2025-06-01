package itc.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaestroModel {

    public Maestro obtenerDatosMaestro(String correo) {
        String sql = "SELECT m.cveMaestro, m.nombre, m.primer_apellido, m.segundo_apellido, " +
                     "c.nombre AS nombre_carrera, m.foto " +
                     "FROM maestro m " +
                     "JOIN carrera c ON m.id_carrera = c.id_carrera " +
                     "JOIN usuario u ON m.id_usuario = u.id_usuario " +
                     "WHERE u.correo = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Maestro(
                    rs.getString("cveMaestro"),
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("nombre_carrera"),
                    rs.getBytes("foto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}