package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection conn;

    public boolean conectar() {
        try {
            // Informing which driver to use
            Class.forName("///////////////");

            // Creating the connection with the database
            conn = DriverManager.getConnection(
                    "jdbc://///////////", "//////////", "////////////"
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
        // Disconnecting from the database
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return conn;
    }

}