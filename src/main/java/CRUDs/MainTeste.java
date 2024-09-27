package CRUDs;

import JDBC.Conexao;

public class MainTeste {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();

        if (conexao.conectar()) {
            System.out.println("Conectado com sucesso");
        } else {
            System.out.println("Erro ao conectar");
        }

        conexao.desconectar();
    }
}
