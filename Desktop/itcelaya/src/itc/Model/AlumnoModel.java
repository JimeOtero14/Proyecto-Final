package itc.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoModel {

    public Alumno obtenerDatosAlumno(String correo) {
        String sql = "SELECT a.noControl, a.nombre, a.primer_apellido, a.segundo_apellido, " +
                     "c.nombre AS nombre_carrera, a.foto " +
                     "FROM alumno a " +
                     "JOIN usuario u ON a.id_usuario = u.id_usuario " +
                     "JOIN carrera c ON a.id_carrera = c.id_carrera " +
                     "WHERE u.correo = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Alumno(
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("nombre_carrera"),
                    String.valueOf(rs.getInt("noControl")),
                    rs.getBytes("foto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
