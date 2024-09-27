package JDBC;
import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private Connection conn;

    public boolean conectar() {
        try {
            // Informando qual driver de conexão será utilizado pelo DriverManager
            Class.forName("org.postgresql.Driver");

            // Criando a conexão com o BD
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbTrocatine", "postgres", "123456"
            );
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return false;
        }
    }

    public void desconectar() {
        // Desconectando do BD
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}