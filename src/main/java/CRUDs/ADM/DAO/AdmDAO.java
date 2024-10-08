package CRUDs.ADM.DAO;
import CRUDs.CADASTRO.DAO.CadastroDAO;
import JDBC.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class AdmDAO {

Conexao conexao = new Conexao();
CadastroDAO cadastro = new CadastroDAO();

public boolean inserirADM(String cidade, String Rua, String Cep, String Complemento, int Numero, String Estado, String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, int adm, String fotoPerfil){
    String sql = "INSERT INTO adm (nome, email, senha, idusuario) VALUES (?, ?, ?, ?)";
    Conexao conexao = new Conexao();

    try {
        int idusuario = cadastro.InserirUsuarioRetornaID(cidade, Rua, Cep, Complemento, Numero, Estado, nome, telefone, senha, trocadinhas, email, cpf, dtNascimento, adm, fotoPerfil);

        if (idusuario > 0){
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setInt(4, idusuario);

            pstmt.executeUpdate();
            return true;
        }else{
            return false;
        }
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }finally {
        conexao.desconectar();
    }
}

public boolean editarSenhaPorID(String novaSenha, int id){
    String sql ="UPDATE adm SET senha = ? WHERE idusuario = ?";
    Conexao conexao = new Conexao();

    try {
        PreparedStatement pstmt = conexao.getConnection().prepareStatement(sql);

        pstmt.setString(1, novaSenha);
        pstmt.setInt(2, id);

        pstmt.executeUpdate();

        return true;
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }finally {
        conexao.desconectar();
    }

}

public void buscarADMPorID(int id){
    String sql = "SELECT * FROM adm WHERE idusuario = ?";
    Conexao conexao = new Conexao();

    try {
        PreparedStatement pstmt = conexao.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            int idusuario = rs.getInt("idusuario");


            System.out.println("Nome: " + nome);
            System.out.println("Email: " + email);
            System.out.println("Senha: " + senha);
            System.out.println("ID do Usuário: " + idusuario);
        } else {
            System.out.println("Nenhum administrador encontrado com o ID: " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        conexao.desconectar();
    }
}

public boolean excluirADMPorID(int id){
    String sql = "DELETE FROM adm WHERE idusuario = ?";
    Conexao conexao = new Conexao();
    try {
        PreparedStatement pstmt = conexao.getConnection().prepareStatement(sql);

        pstmt.setInt(1, id);

        pstmt.executeUpdate();
        return true;
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }finally {
        conexao.desconectar();
    }

}


}
