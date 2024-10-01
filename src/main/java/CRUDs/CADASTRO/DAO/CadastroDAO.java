package CRUDs.CADASTRO.DAO;

import JDBC.Conexao;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CadastroDAO {

    Conexao conexao = new Conexao();

    public boolean InserirEndereco(String cidade, String Rua, int Cep, String Complemento, int Numero, String Estado) throws SQLException {
        String sql = "INSERT INTO endereco (Cidade, Rua, Cep, Complemento, Numero, Estado) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conexao.conectar();
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(sql);

            pstmt.setString(1, cidade);
            pstmt.setString(2, Rua);
            pstmt.setInt(3, Cep);
            pstmt.setString(4, Complemento);
            pstmt.setInt(5, Numero);
            pstmt.setString(6, Estado);
            pstmt.executeUpdate();

            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            conexao.desconectar();
        }
    }

    public void InserirUsuario(String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil, String endereco) throws SQLException {
        String sql = "INSERT INTO usuario (nome, telefone, senha, trocadinhas, email, cpf, dt_nascimento, adm, foto_perfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = sdf.parse(dtNascimento);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, senha);
            stmt.setInt(4, trocadinhas);
            stmt.setString(5, email);
            stmt.setString(6, cpf);
            stmt.setDate(7, sqlDate);
            stmt.setBoolean(8, adm);
            stmt.setString(9, fotoPerfil);

            // Executa a inserção no banco
            stmt.executeUpdate();
        } catch (SQLException | ParseException e) {
            throw new SQLException(e);
        } finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }

    public void excluirUsuarioPorID(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
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

    public void buscarUsuarioPorID(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
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
            conexao.conectar();
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
            conexao.conectar();
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
