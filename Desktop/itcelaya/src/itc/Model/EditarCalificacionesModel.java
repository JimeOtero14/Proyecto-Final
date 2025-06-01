package itc.Model;

import itc.Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditarCalificacionesModel {

    public List<String> obtenerMateriasPorMaestro(String cveMaestro) {
        List<String> materias = new ArrayList<>();
        String sql = "SELECT DISTINCT m.nombre FROM materia m " +
                "JOIN grupo g ON m.id_materia = g.id_materia " +
                "WHERE g.cveMaestro = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();

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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();

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

    public List<Integer> obtenerParcialesRegistrados(int noControl, int idGrupo, String cveMaestro, String nombreMateria) {
        List<Integer> parciales = new ArrayList<>();
        String sql = "SELECT id_parcial FROM inscrito i " +
                "JOIN materia m ON i.id_materia = m.id_materia " +
                "WHERE i.noControl = ? AND i.id_grupo = ? AND i.cveMaestro = ? AND m.nombre = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setString(4, nombreMateria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    parciales.add(rs.getInt("id_parcial"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parciales;
    }

    public Integer obtenerCalificacion(int noControl, int idGrupo, String cveMaestro, String nombreMateria, int parcial) {
        String sql = "SELECT calificacion FROM inscrito i " +
                "JOIN materia m ON i.id_materia = m.id_materia " +
                "WHERE i.noControl = ? AND i.id_grupo = ? AND i.cveMaestro = ? AND m.nombre = ? AND i.id_parcial = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setString(4, nombreMateria);
            ps.setInt(5, parcial);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("calificacion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrarCalificacion(int noControl, int idGrupo, String cveMaestro,
                                        String nombreMateria, int parcial, int calificacion) {
        String sqlMateria = "SELECT id_materia FROM materia WHERE nombre = ?";
        int idMateria = -1;

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement psMat = conn.prepareStatement(sqlMateria)) {

            psMat.setString(1, nombreMateria);
            try (ResultSet rs = psMat.executeQuery()) {
                if (rs.next()) {
                    idMateria = rs.getInt("id_materia");
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "INSERT INTO inscrito (noControl, id_grupo, cveMaestro, id_materia, id_parcial, calificacion) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE calificacion = VALUES(calificacion)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setInt(4, idMateria);
            ps.setInt(5, parcial);
            ps.setInt(6, calificacion);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
