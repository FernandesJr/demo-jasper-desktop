package jdbc;

import com.mysql.jdbc.Connection;
import net.sf.jasperreports.engine.JRException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, JRException, IOException {
        //mostrarRelatorio("09");
        //exportPdf("02", "C:\\Users\\Junior Fernandes\\Documents\\jasper-"+ UUID.randomUUID()+".pdf");
        //abrirPontoJasper("02");
        //jasperComSubRelatorio("10");
        abrirJrxmlSubRelatorios("10");
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

    public static void abrirPontoJasper(String numero) throws SQLException, JRException {
        Connection connection = (Connection) Conexao.getConnection();
        JasperService service = new JasperService();
        //service.adicionarParam("NIVEL_DESC", "SENIOR");
        //service.adicionarParam("UF", "RN");
        service.abrirJasper("relatorios/jasper/funcionario-"+numero+".jasper", connection);
        connection.close();
    }

    public static void jasperComSubRelatorio(String numero) throws SQLException, JRException {
        Connection connection = (Connection) Conexao.getConnection();
        JasperService service = new JasperService();
        service.adicionarParam("SUB_REPORT_DIR", "relatorios/jasper/");
        service.abrirJasper("relatorios/jasper/funcionario-"+numero+".jasper", connection);
        connection.close();
    }

    public static void abrirJrxmlSubRelatorios(String numero) throws SQLException, JRException {
        Connection connection = (Connection) Conexao.getConnection();
        JasperService service = new JasperService();
        service.abrirJrxmlComSubReport("relatorios/jrxml/funcionario-"+numero+".jrxml",
                "relatorios/jrxml/funcionario-"+numero+"-subfones.jrxml",
                connection);
        connection.close();
    }
}
