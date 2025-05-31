package itc.Model;

import itc.Model.Calificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarAlumnosModel {

    public List<String> obtenerMateriasPorMaestro(String cveMaestro) {
        List<String> materias = new ArrayList<>();
        String sql = "SELECT DISTINCT m.nombre FROM materia m " +
                "JOIN grupo g ON m.id_materia = g.id_materia " +
                "WHERE g.cveMaestro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cveMaestro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    materias.add(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public List<String> obtenerGruposPorMateriaYMaestro(String nombreMateria, String cveMaestro) {
        List<String> grupos = new ArrayList<>();
        String sql = "SELECT g.id_grupo FROM grupo g " +
                "JOIN materia m ON g.id_materia = m.id_materia " +
                "WHERE m.nombre = ? AND g.cveMaestro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreMateria);
            ps.setString(2, cveMaestro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    grupos.add(String.valueOf(rs.getInt("id_grupo")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public List<String> obtenerAlumnosPorGrupo(int idGrupo) {
        List<String> alumnos = new ArrayList<>();
        String sql = "SELECT a.noControl, a.nombre, a.primer_apellido FROM alumno a " +
                "JOIN inscrito i ON a.noControl = i.noControl " +
                "WHERE i.id_grupo = ? " +
                "GROUP BY a.noControl, a.nombre, a.primer_apellido";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idGrupo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alumnos.add(rs.getInt("noControl") + " - " + rs.getString("nombre") + " " + rs.getString("primer_apellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public List<Calificacion> obtenerCalificaciones(int noControl, int idGrupo, String cveMaestro, String nombreMateria) {
        List<Calificacion> calificaciones = new ArrayList<>();

        String sql = "SELECT i.id_parcial, i.calificacion FROM inscrito i " +
                     "JOIN materia m ON i.id_materia = m.id_materia " +
                     "WHERE i.noControl = ? AND i.id_grupo = ? AND i.cveMaestro = ? AND m.nombre = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setString(4, nombreMateria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int parcial = rs.getInt("id_parcial");
                    int calif = rs.getInt("calificacion");
                    calificaciones.add(new Calificacion(parcial, calif));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calificaciones;
    }
}
