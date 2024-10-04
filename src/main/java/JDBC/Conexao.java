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
                Class.forName("org.postgresql.Driver");

                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbTrocatine", "postgres", "123456");
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