package itc.Model.DAO;

import itc.Model.Alumno;
import itc.Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insertar(Alumno alumno) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO alumno(noControl, nombre, primer_apellido, segundo_apellido, id_carrera, foto) " +
                "VALUES (?, ?, ?, ?, (SELECT id_carrera FROM carrera WHERE nombre = ?), ?)"
            );
            stmt.setString(1, alumno.getNoControl());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getPrimerApellido());
            stmt.setString(4, alumno.getSegundoApellido());
            stmt.setString(5, alumno.getCarrera());
            stmt.setBytes(6, alumno.getFoto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumno> obtenerTodos() {
        List<Alumno> lista = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT a.noControl, a.nombre, a.primer_apellido, a.segundo_apellido, a.foto, c.nombre AS carrera " +
                "FROM alumno a JOIN carrera c ON a.id_carrera = c.id_carrera"
            );
            while (rs.next()) {
                lista.add(new Alumno(
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("carrera"),
                    rs.getString("noControl"),
                    rs.getBytes("foto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Alumno buscarPorNoControl(String noControl) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT a.noControl, a.nombre, a.primer_apellido, a.segundo_apellido, a.foto, c.nombre AS carrera " +
                "FROM alumno a JOIN carrera c ON a.id_carrera = c.id_carrera WHERE a.noControl = ?"
            );
            stmt.setString(1, noControl);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Alumno(
                    rs.getString("nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("carrera"),
                    rs.getString("noControl"),
                    rs.getBytes("foto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
