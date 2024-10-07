package CRUDs.CADASTRO.DAO;

import JDBC.Conexao;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CadastroDAO {

    Conexao conexao = new Conexao();

    public boolean InserirUsuario(String cidade, String Rua, String Cep, String Complemento, int Numero, String Estado, String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil) throws SQLException {
        String sql2 = "INSERT INTO usuario (nome, telefone, senha, trocadinhas, email, cpf, dt_nascimento, adm, foto_perfil, idendereco) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Conexao conexao = new Conexao();

        try {

            int idendereco = InserirEnderecoRetornaId(cidade, Rua, Cep, Complemento, Numero, Estado, conexao);
            boolean
            if (idendereco > 0) {

                try (PreparedStatement stmt2 = conexao.getConnection().prepareStatement(sql2)) {
                    stmt2.setString(1, nome);
                    stmt2.setString(2, telefone);
                    stmt2.setString(3, senha);
                    stmt2.setInt(4, trocadinhas);
                    stmt2.setString(5, email);
                    stmt2.setString(6, cpf);
                    Date dataNascimento = Date.valueOf(dtNascimento);
                    stmt2.setDate(7, dataNascimento);
                    stmt2.setBoolean(8, adm);
                    stmt2.setString(9, fotoPerfil);
                    stmt2.setInt(10, idendereco);

                    stmt2.executeUpdate();
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexao.desconectar();
        }
    }

    public int InserirUsuarioRetornaID(String cidade, String Rua, String Cep, String Complemento, int Numero, String Estado, String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil){
        String sql = "INSERT INTO usuario (nome, telefone, senha, trocadinhas, email, cpf, dt_nascimento, adm, foto_perfil, idendereco) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Conexao conexao = new Conexao();
        int generatedId = -1;

        try {
            int idendereco = InserirEnderecoRetornaId(cidade, Rua, Cep, Complemento, Numero, Estado, conexao);

            boolean adm2 = true;

            if (idendereco > 0) {
                try (PreparedStatement stmt2 = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt2.setString(1, nome);
                    stmt2.setString(2, telefone);
                    stmt2.setString(3, senha);
                    stmt2.setInt(4, trocadinhas);
                    stmt2.setString(5, email);
                    stmt2.setString(6, cpf);
                    Date dataNascimento = Date.valueOf(dtNascimento);
                    stmt2.setDate(7, dataNascimento);
                    stmt2.setBoolean(8, adm2);
                    stmt2.setString(9, fotoPerfil);
                    stmt2.setInt(10, idendereco);

                    int affectedRows = stmt2.executeUpdate();

                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = stmt2.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                generatedId = generatedKeys.getInt(5);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar();
        }

        return generatedId;
    }

    public int InserirEnderecoRetornaId(String cidade, String Rua, String Cep, String Complemento, int Numero, String Estado, Conexao conexao) throws SQLException {
        String sql = "INSERT INTO endereco (Cidade, Rua, Cep, Complemento, Numero, Estado) VALUES (?,?,?,?,?,?)";

        try {

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cidade);
            stmt.setString(2, Rua);
            stmt.setString(3, Cep);
            stmt.setString(4, Complemento);
            stmt.setInt(5, Numero);
            stmt.setString(6, Estado);

            stmt.executeUpdate();


            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(2);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public void excluirUsuarioPorID(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.getConnection();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }
    public void mostrarTodosUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        Conexao conexao = new Conexao();

        try {
            conexao.getConnection();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String senha = rs.getString("senha");
                    int trocadinhas = rs.getInt("trocadinhas");
                    String email = rs.getString("email");
                    String cpf = rs.getString("cpf");
                    String dtNascimento = rs.getString("dt_nascimento");
                    boolean adm = rs.getBoolean("adm");
                    String fotoPerfil = rs.getString("foto_perfil");
                    System.out.println("ID: " + id + " Nome: " + nome + " Telefone: " + telefone + " Senha: " + senha + " Trocadinhas: " + trocadinhas + " Email: " + email + " CPF: " + cpf + " Data de Nascimento: " + dtNascimento + " Administrador: " + adm + " Foto do Perfil: " + fotoPerfil);
            }

        }   catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
            }

    }
    public void buscarUsuarioPorID(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.getConnection();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                int trocadinhas = rs.getInt("trocadinhas");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String dtNascimento = rs.getString("dt_nascimento");
                boolean adm = rs.getBoolean("adm");
                String fotoPerfil = rs.getString("foto_perfil");
                String endereco = rs.getString("endereco");
                int idTabela1 = rs.getInt("idTabela1");
                int idTabela2 = rs.getInt("idTabela2");
            }
        }catch (SQLException e){
            throw new SQLException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }

    public void buscarUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.getConnection();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                int trocadinhas = rs.getInt("trocadinhas");
                String cpf = rs.getString("cpf");
                String dtNascimento = rs.getString("dt_nascimento");
                boolean adm = rs.getBoolean("adm");
                String fotoPerfil = rs.getString("foto_perfil");
                String endereco = rs.getString("endereco");
                int idTabela1 = rs.getInt("idTabela1");
                int idTabela2 = rs.getInt("idTabela2");
            }
        }catch (SQLException e) {
            throw new SQLException(e);
        }finally{
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }

    public void buscarUsuarioPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        Conexao conexao = new Conexao();
        try {
            conexao.getConnection();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                int trocadinhas = rs.getInt("trocadinhas");
                String email = rs.getString("email");
                String dtNascimento = rs.getString("dt_nascimento");
                boolean adm = rs.getBoolean("adm");
                String fotoPerfil = rs.getString("foto_perfil");
                String endereco = rs.getString("endereco");
                int idTabela1 = rs.getInt("idTabela1");
                int idTabela2 = rs.getInt("idTabela2");
            }
        }catch (SQLException e) {
            throw new SQLException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }
}
