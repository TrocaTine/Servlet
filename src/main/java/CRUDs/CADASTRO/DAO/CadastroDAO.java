package CRUDs.CADASTRO.DAO;

import JDBC.Conexao;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CadastroDAO {

    Conexao conexao = new Conexao();

    public void InserirUsuario(String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil, String endereco, int idTabela1, int idTabela2) throws SQLException {
        String sql = "INSERT INTO usuario (nome, telefone, senha, trocadinhas, email, cpf, dt_nascimento, adm, foto_perfil, endereco, idTabela1, idTabela2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            stmt.setString(10, endereco);
            stmt.setInt(11, idTabela1);
            stmt.setInt(12, idTabela2);


            stmt.executeUpdate();
        } catch (SQLException | ParseException e) {
            throw new SQLException(e);
        } finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }


    }


    public void editarNomePorID(String nome, int id) throws SQLException {
        String sql = "UPDATE usuario SET nome = ? WHERE id = ?";

        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }


    public void editarTelefonePorID(String telefone, int id) throws SQLException {
        String sql = "UPDATE usuario SET telefone = ? WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, telefone);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }


    public void editarSenhaPorID(String senha, int id) throws SQLException {
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, senha);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }


    public void editarEmailPorID(String email, int id) throws SQLException {
        String sql = "UPDATE usuario SET email = ? WHERE id = ?";
        Conexao conexao = new Conexao();

        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }finally {
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
}
