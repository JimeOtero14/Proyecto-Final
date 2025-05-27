package itc.Model;
//commit
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/itcelaya";
    private static final String USER = "egoskz";
    private static final String PASSWORD = "6dmg4pvz";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC");
            throw new SQLException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }
}