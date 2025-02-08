package Forms;

import Controller.BrrowedController;
import Controller.ReturnBooks_Controoler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Brrowedbooks;
import model.Return_Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Staff;

import java.net.URL;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnBooks_Form implements Initializable {


    public ComboBox combo_browid;
    public TextField txt_bookid;
    public TextField txt_Member_id;
    public TextField txt_Staff_id;
    public TextField txt_Broowed_date;
    public TextField is_Browwed;
    public Label lable_Date;
    public TextField txt_damage_or_No;
    public TextField txt_Amount;
    public TextField return_on_time;
    public TextField txt_returnid;
    public TableView tabel_return;
    public TableColumn colum_returnid;
    public TableColumn colum_browwedid;
    public TableColumn colum_bookid;
    public TableColumn colum_returndate;
    public TableColumn colum_fineAmount;
    public TableColumn colum_Return_Status;
    BrrowedController brrowedController=new BrrowedController();
    ReturnBooks_Controoler returnBooksControoler=new ReturnBooks_Controoler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        get_comboigd();
        LodDate();

    }

    public void get_comboigd() {
        ObservableList<String>get_Mmemberid=FXCollections.observableArrayList();
        for (Brrowedbooks brrowedbooks :brrowedController.getInstance().getAll()){
            get_Mmemberid.add(brrowedbooks.getBrrowedbokksid());
            combo_browid.setItems(get_Mmemberid);
        }


    }

    public void combo_Browedid_Action(ActionEvent actionEvent) {
        String selectid =(String) combo_browid.getSelectionModel().getSelectedItem();
        Brrowedbooks brrowedbooks=brrowedController.searchMember(selectid);

        txt_bookid.setText(brrowedbooks.getBookID());
        txt_Member_id.setText(brrowedbooks.getMemberid());
        txt_Staff_id.setText(brrowedbooks.getStaffid());
        txt_Broowed_date.setText(brrowedbooks.getBrooedate());
        is_Browwed.setText(brrowedbooks.getIsBrowwed());

    }

    private void LodDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        lable_Date.setText(f.format(date));


    }


    public void btn_Action_Find(ActionEvent actionEvent) {
        try {
            // Validate selection first
            if (combo_browid.getSelectionModel().getSelectedItem() == null) {
                txt_damage_or_No.setText("No Borrow ID Selected");
                return;
            }

            String selectid = (String) combo_browid.getSelectionModel().getSelectedItem();
            Brrowedbooks brrowedbooks = brrowedController.searchMember(selectid);

            if (brrowedbooks == null) {
                txt_damage_or_No.setText("No Borrow Record Found");
                return;
            }


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date borrowedDate = dateFormat.parse(txt_Broowed_date.getText());
            Date returnDate = new Date();

            long diffInMillies = Math.abs(returnDate.getTime() - borrowedDate.getTime());
            long daysDiff = diffInMillies / (24 * 60 * 60 * 1000);

            double fine = 0;
            if (daysDiff > 10) {
                long daysOverdue = daysDiff - 10;
                fine = daysOverdue * 10;

               return_on_time.setText("Overdue: " + daysOverdue + " days");
                txt_Amount.setText(String.format("%.2f", fine));
                return_on_time.setText("Late Return");
            } else {
                return_on_time.setText("On Time");
                txt_Amount.setText("0.00");
                return_on_time.setText("Returned ");
            }
        } catch (Exception e) {
            return_on_time.setText("Error: " + e.getMessage());
            txt_Amount.setText("0.00");
            e.printStackTrace();
        }

        returnBooksControoler.addBooks(new Return_Book(
                txt_returnid.getText(),
                (String) combo_browid.getValue(),
                txt_bookid.getText(),
                lable_Date.getText(),
               Double.valueOf(txt_Amount.getText()),
                return_on_time.getText()



        ));
    }

    public void btn_View_Action(ActionEvent actionEvent) {
        colum_returnid.setCellValueFactory(new PropertyValueFactory<>("returnid"));
        colum_browwedid.setCellValueFactory(new PropertyValueFactory<>("borrow_id"));
        colum_bookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colum_returndate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        colum_fineAmount.setCellValueFactory(new PropertyValueFactory<>("findAmount"));
        colum_Return_Status.setCellValueFactory(new PropertyValueFactory<>("return_status"));
        Lodtabel();



    }
    public void Lodtabel(){
            List<Return_Book> returnBooks=returnBooksControoler.getAll();
            System.out.println(returnBooks);
            ObservableList<Return_Book> observableList= FXCollections.observableArrayList();
            returnBooks.forEach(returnBook -> observableList.add(returnBook));
            tabel_return.setItems(observableList);
        }
        }


