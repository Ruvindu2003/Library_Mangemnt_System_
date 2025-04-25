package Controller;

import db.DBConnection;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;



public class ReportController {
    public static ReportController insntance;

    public ReportController getInsntance() {
        if (insntance == null) {
            insntance = new ReportController();
        }
        return insntance;
    }

    public void Generate_Reportby_Count(String ReportPath, String Filename, String query) {
        try {
            JasperDesign report = JRXmlLoader.load(ReportPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(query);
            report.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, Filename);
            JasperViewer.viewReport(jasperPrint, false);
                new  Alert(Alert.AlertType.INFORMATION,"Success Fully !").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Report Generation Failed!.").show();
            throw new RuntimeException(e);
        }


    }

}
