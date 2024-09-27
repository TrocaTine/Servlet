package CRUDs.CADASTRO.DAO;

import JDBC.Conexao;

import java.sql.*;

public class CadastroDAO {

    Conexao conexao = new Conexao();

    public void InserirUsuario(String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil, String endereco, int idTabela1, int idTabela2) throws SQLException {
        String sql = "INSERT INTO usuario (nome, telefone, senha, trocadinhas, email, cpf, dt_nascimento, adm, foto_perfil, endereco, idTabela1, idTabela2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();


        try {
            conexao.conectar();
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, senha);
            stmt.setInt(4, trocadinhas);
            stmt.setString(5, email);
            stmt.setString(6, cpf);
            stmt.setString(7, dtNascimento);
            stmt.setBoolean(8, adm);
            stmt.setString(9, fotoPerfil);
            stmt.setString(10, endereco);
            stmt.setInt(11, idTabela1);
            stmt.setInt(12, idTabela2);


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            if (conexao.getConnection() != null) {
                conexao.desconectar();
            }
        }
    }
}
