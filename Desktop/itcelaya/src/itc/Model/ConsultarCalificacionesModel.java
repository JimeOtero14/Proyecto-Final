package itc.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarCalificacionesModel {

    public static class CalificacionDetalle {
        private String nombreMateria;
        private String nombreProfesor;
        private int idGrupo;
        private int parcial;
        private int calificacion;
        private String oportunidad;

        public CalificacionDetalle(String nombreMateria, String nombreProfesor, int idGrupo, int parcial, int calificacion, String oportunidad) {
            this.nombreMateria = nombreMateria;
            this.nombreProfesor = nombreProfesor;
            this.idGrupo = idGrupo;
            this.parcial = parcial;
            this.calificacion = calificacion;
            this.oportunidad = oportunidad;
        }

        public String getNombreMateria() {
            return nombreMateria;
        }

        public String getNombreProfesor() {
            return nombreProfesor;
        }

        public int getIdGrupo() {
            return idGrupo;
        }

        public int getParcial() {
            return parcial;
        }

        public int getCalificacion() {
            return calificacion;
        }

        public String getOportunidad() {
            return oportunidad;
        }
    }

    public List<CalificacionDetalle> obtenerCalificacionesAlumno(int noControl) {
        List<CalificacionDetalle> lista = new ArrayList<>();

        String sql = "SELECT m.nombre AS nombreMateria, " +
                     "CONCAT(ma.nombre, ' ', ma.primer_apellido, ' ', IFNULL(ma.segundo_apellido, '')) AS nombreProfesor, " +
                     "i.id_grupo, i.id_parcial, i.calificacion, i.oportunidad " +
                     "FROM inscrito i " +
                     "JOIN materia m ON i.id_materia = m.id_materia " +
                     "JOIN maestro ma ON i.cveMaestro = ma.cveMaestro " +
                     "WHERE i.noControl = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noControl);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new CalificacionDetalle(
                        rs.getString("nombreMateria"),
                        rs.getString("nombreProfesor").trim(),
                        rs.getInt("id_grupo"),
                        rs.getInt("id_parcial"),
                        rs.getInt("calificacion"),
                        rs.getString("oportunidad")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
