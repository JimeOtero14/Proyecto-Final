package itc.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KardexModel {

    public boolean yaRegistradoEnKardex(int noControl, int idGrupo, String cveMaestro, int idMateria) {
        String sql = "SELECT COUNT(*) FROM kardex WHERE noControl = ? AND id_grupo = ? AND cveMaestro = ? AND id_materia = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setInt(4, idMateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registrarEnKardex(int noControl, int idGrupo, String cveMaestro, int idMateria, double califCardex) {
        if (yaRegistradoEnKardex(noControl, idGrupo, cveMaestro, idMateria)) {
            return false;
        }

        String sql = "INSERT INTO kardex (noControl, id_grupo, cveMaestro, id_materia, califCardex) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, noControl);
            ps.setInt(2, idGrupo);
            ps.setString(3, cveMaestro);
            ps.setInt(4, idMateria);
            ps.setDouble(5, califCardex);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
