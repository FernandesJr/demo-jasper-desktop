package jdbc;

import com.mysql.jdbc.Connection;
import net.sf.jasperreports.engine.JRException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, JRException, IOException {
        //mostrarRelatorio("09");
        exportPdf("02", "C:\\Users\\Junior Fernandes\\Documents\\jasper-"+ UUID.randomUUID()+".pdf");
    }

    public static void mostrarRelatorio(String numero) throws SQLException, JRException {
        Connection connection = (Connection) Conexao.getConnection();
        JasperService service = new JasperService();
        service.adicionarParam("NIVEL_DESC", "SENIOR");
        service.adicionarParam("UF", "RN");
        service.abrirJasperView("relatorios/jrxml/funcionario-"+numero+".jrxml", connection);
        connection.close();
    }

    public static void exportPdf(String numero, String caminhoSalvar) throws SQLException, JRException, IOException {
        Connection connection = (Connection) Conexao.getConnection();
        JasperService service = new JasperService();
        service.exportaPDF("relatorios/jrxml/funcionario-"+numero+".jrxml", connection, caminhoSalvar);
        connection.close();
        Desktop.getDesktop().open(new File(caminhoSalvar));
    }
}
