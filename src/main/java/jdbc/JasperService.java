package jdbc;

import com.mysql.jdbc.Connection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class JasperService {

    private Map<String, Object> params = new LinkedHashMap<>();

    public void adicionarParam(String key, Object value){
        this.params.put(key, value);
    }

    private JasperReport compilejrxml(String arquivo) throws JRException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo); //Caminho do file
        return JasperCompileManager.compileReport(is); //Compilando
    }

    public void abrirJasperView(String caminhoJrxml, Connection connection) throws JRException {
        JasperReport report = this.compilejrxml(caminhoJrxml); //Compilando
        JasperPrint print = JasperFillManager.fillReport(report,params,connection);
        JasperViewer viewer = new JasperViewer(print);
        viewer.setVisible(true);
    }

    public void exportaPDF(String caminhoJrxml, Connection connection, String destino) throws JRException, FileNotFoundException {
        JasperReport report = this.compilejrxml(caminhoJrxml); //Compilando
        JasperPrint print = JasperFillManager.fillReport(report,params,connection);
        OutputStream outputStream = new FileOutputStream(destino); //Caminho que será salvo o relatório
        JasperExportManager.exportReportToPdfStream(print, outputStream);
    }



}
