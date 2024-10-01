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

        String cidade = "São Paulo";
        String estado = "SP";
        String rua = "av. Bela Vista";
        String complemento = "apto 101";
        int cep = 01001000;
        int numero = 100;




        CadastroDAO cadastro = new CadastroDAO();

        try {
            if (cadastro.InserirEndereco(cidade, rua, cep, complemento, numero, estado)){
                System.out.println("Inserido com sucesso");
            }else {
                System.out.println("Erro ao inserir endereco");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
