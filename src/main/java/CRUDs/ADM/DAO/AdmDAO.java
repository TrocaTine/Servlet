package CRUDs.ADM.DAO;
import CRUDs.CADASTRO.DAO.CadastroDAO;
import JDBC.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class AdmDAO {

Conexao conexao = new Conexao();
CadastroDAO cadastro = new CadastroDAO();

public boolean inserirADM(String cidade, String Rua, String Cep, String Complemento, int Numero, String Estado, String nome, String telefone, String senha, int trocadinhas, String email, String cpf, String dtNascimento, boolean adm, String fotoPerfil){
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



}
