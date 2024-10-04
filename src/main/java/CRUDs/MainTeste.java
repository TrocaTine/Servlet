package CRUDs;
import CRUDs.CADASTRO.DAO.CadastroDAO;
import JDBC.Conexao;
import java.sql.*;

public class MainTeste {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        CadastroDAO cadastro = new CadastroDAO();

        if (conexao.getConnection()!=null) {
            System.out.println("Conectado com sucesso");
        } else {
            System.out.println("Erro ao conectar");
        }

        conexao.desconectar();

        // Dados de exemplo
        String cidade = "São Paulo";
        String rua = "Rua Exemplo";
        String cep = "12345678";
        String complemento = "Apto 101";
        int numero = 100;
        String estado = "SP";

        // Dados do usuário
        String nome = "Carlos Gonzalez";
        String telefone = "123456789";
        String senha = "senha123";
        int trocadinhas = 50;
        String email = "carlos@example.com";
        String cpf = "123.456.789-00";
        String dtNascimento = "1990-01-01";
        boolean adm = false;
        String fotoPerfil = "perfil.jpg";

        try {
            // Chamar o método de inserção do usuário
            boolean sucesso = cadastro.InserirUsuario(cidade, rua, cep, complemento, numero, estado, nome, telefone, senha, trocadinhas, email, cpf, dtNascimento, adm, fotoPerfil);

            if (sucesso) {
                System.out.println("Usuário inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir o usuário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        //try {
            // Replace with actual values for user data
            //String nome = "John Doe";
            //String telefone = "1234567890";
            //String senha = "password123";
            //int trocadinhas = 100;
            //String email = "johndoe@example.com";
            //String cpf = "12345678901";
            //String dtNascimento = "1990-01-01";
            //boolean adm = false;
            //String fotoPerfil = "path/to/profile_photo.jpg";
            //String endereco = "123 Main Street";

            // Create an instance of your class and call the method
            //CadastroDAO cadastro = new CadastroDAO();
            //cadastro.InserirUsuario(nome, telefone, senha, trocadinhas, email, cpf, dtNascimento, adm, fotoPerfil, endereco);

          //  System.out.println("User inserted successfully!");
        //} catch (SQLException e) {
          //  e.printStackTrace();
          //  System.err.println("Error inserting user: " + e.getMessage());
        //}



}
