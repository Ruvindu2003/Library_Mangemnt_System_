package Forms;

import Controller.ReportController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Report_Form {
    public CheckBox chbox_books;
    public CheckBox chombox_member;
    public CheckBox chobox_returnbooks;
    public CheckBox chobox_borrowedbooks;
    public TextField txt_book_count;
    public DatePicker dt_browwedboks;
    public TextField txt_bb_nowdate;
    public DatePicker dt_returned_books;
    public TextField txt_rb_nowdate;
    public TextField txt_member_count;

    ReportController reportController=new ReportController();

    public void btn_gemerateReporton_Action(ActionEvent actionEvent) {
        if(Integer.parseInt(txt_book_count.getText())<0){
            reportController.Generate_Reportby_Count("src/main/resources/reports/Addbook3.jrxml","booksreport.pdf","SELECT * FROM books LIMIT 5");

        }else {

        }


        reportController.Generate_Reportby_member("src/main/resources/Reports/Addmember4.jrxml/","member.pdf","SELECT * FROM members LIMIT 5");
    }


}
