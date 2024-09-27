package CRUDs;
import CRUDs.CADASTRO.DAO.CadastroDAO;
import JDBC.Conexao;
import java.sql.*;

public class MainTeste {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();

        if (conexao.conectar()) {
            System.out.println("Conectado com sucesso");
        } else {
            System.out.println("Erro ao conectar");
        }

        conexao.desconectar();

        try {
            // Replace with actual values for user data
            String nome = "John Doe";
            String telefone = "1234567890";
            String senha = "password123";
            int trocadinhas = 100;
            String email = "johndoe@example.com";
            String cpf = "12345678901";
            String dtNascimento = "1990-01-01";
            boolean adm = false;
            String fotoPerfil = "path/to/profile_photo.jpg";
            String endereco = "123 Main Street";
            int idTabela1 = 1;
            int idTabela2 = 2;

            // Create an instance of your class and call the method
            CadastroDAO cadastro = new CadastroDAO();
            cadastro.InserirUsuario(nome, telefone, senha, trocadinhas, email, cpf, dtNascimento, adm, fotoPerfil, endereco, idTabela1, idTabela2);

            System.out.println("User inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error inserting user: " + e.getMessage());
        }
    }
}
