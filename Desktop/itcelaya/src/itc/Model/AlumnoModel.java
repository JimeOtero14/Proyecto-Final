package itc.Model;

import itc.Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoModel {
    //commit
    public Alumno obtenerDatosAlumno(String correo) {
        String sql = "SELECT a.noControl, a.nombre, a.primer_apellido, a.segundo_apellido, c.nombre as nombre_carrera " +
                     "FROM alumno a " +
                     "JOIN usuario u ON a.id_usuario = u.id_usuario " +
                     "JOIN carrera c ON a.id_carrera = c.id_carrera " +
                     "WHERE u.correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Alumno(
                    rs.getInt("noControl"),
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("nombre_carrera")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
