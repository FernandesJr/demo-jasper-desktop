package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() throws SQLException{
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaspercurso", "root", "root");
            System.out.println("conectado");
        } catch (SQLException e){
            System.out.println("Erro:" + e);
        }
        return conexao;
    }
}
