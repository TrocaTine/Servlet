package JDBC;
import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private Connection conn;

    public Connection getConnection() {
        if (conn == null) {
            try {
                String driver = System.getenv("DRIVER");
                Class.forName(driver);

                String url = System.getenv("DB_URL");
                String user = System.getenv("DB_USER");
                String password = System.getenv("DB_PASSWORD");

                conn = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public void desconectar() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}