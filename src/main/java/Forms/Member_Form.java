package Forms;

import Controller.MemberController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Members;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import servicess.ServiceFactory;
import servicess.custom.Memeber_Service;
import utill.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.*;

public class Member_Form implements Initializable {

    public Label lbl_Date;
    public TextField txt_memberid;
    public TextField txt_membername;
    public TextField txt_phoneNumber;
    public TextField txt_Adrees;
    public TableColumn colum_memberid;
    public TableColumn colum_full_name;
    public TableColumn colum_PhoneNumber;
    public TableColumn colum_Adreess;
    public TableColumn colum_Headmastership;
    public TableView table_Memeber;

    MemberController memberController=new MemberController();

    Memeber_Service getInstance= ServiceFactory.getInstance().getServiceType(ServiceType.AddMemeber);

    private void LodDate(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        lbl_Date.setText(f.format(date));
        

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LodDate();


    }

    public void btn_Search_Action(ActionEvent actionEvent) {
        Members members=getInstance.searchMember(txt_memberid.getText());
        if (members!=null){


        }
        txt_membername.setText(String.valueOf(members.getName()));
        txt_phoneNumber.setText(String.valueOf(members.getPhoneNumber()));
        txt_Adrees.setText(String.valueOf(members.getAdrees()));
        lbl_Date.setText(String.valueOf(members.getMembershipdates()));
    }

    public void btn_Add_Action(ActionEvent actionEvent) {

        getInstance.AddMemeber(new Members(
                txt_memberid.getText(),
                txt_membername.getText(),
                txt_phoneNumber.getText(),
                txt_Adrees.getText(),
                lbl_Date.getText()
        ));


    }

    public void btn_View_Action(ActionEvent actionEvent) {

        colum_memberid.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        colum_full_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        colum_PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colum_Adreess.setCellValueFactory(new PropertyValueFactory<>("adrees"));
        colum_Headmastership.setCellValueFactory(new PropertyValueFactory<>("Membershipdates"));
        LodTable();

    }

    public void btn_update_Action(ActionEvent actionEvent) {
        boolean b=getInstance.updateMemeber(new Members(
                txt_memberid.getText(),
                txt_membername.getText(),
                txt_phoneNumber.getText(),
                txt_Adrees.getText(),
                lbl_Date.getText()
        ));

    }


    public void btn_Delete_Action(ActionEvent actionEvent) {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("Delete from members  where member_id =?");

            preparedStatement.setString(1,txt_memberid.getText());

            if (0 < preparedStatement.executeUpdate()){
                new Alert(Alert.AlertType.INFORMATION,"Sucsse Fully Delete").show();

            }else {
                new  Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full Delete").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void LodTable(){
        ArrayList<Members> all = (ArrayList<Members>)getInstance.getAll();
        System.out.println(all);
        ObservableList<Members> objects = FXCollections.observableArrayList();
        all.forEach(members -> objects.add(members));
        table_Memeber.setItems(objects);


    }

    public void btn_Back_Action(ActionEvent actionEvent){
        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
