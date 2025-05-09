package Forms;



import Controller.IDgenrateController;
import Controller.StaffController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Members;
import model.Staff;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Entity;
import servicess.ServiceFactory;
import servicess.custom.Staff_Service;
import utill.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Staff_Form implements Initializable {
    public AnchorPane Ancor_Staff;
    StaffController staffController=new StaffController();
    IDgenrateController iDgenrateController=IDgenrateController.getInstance();

    Staff_Service getInstance= ServiceFactory.getInstance().getServiceType(ServiceType.AddStaff);

        @FXML
        private TableColumn<?, ?> colum_PhoneNumber;

        @FXML
        private TableColumn<?, ?> colum_Staffid;

        @FXML
        private TableColumn<?, ?> colum_email;

        @FXML
        private TableColumn<?, ?> colum_name;

        @FXML
        private TableView<Staff> table_Staff;

        @FXML
        private TextField txt_Staffid;

        @FXML
        private TextField txt_email;

        @FXML
        private TextField txt_name;

        @FXML
        private TextField txt_phoneNumber;

    int staffCount = 1;
    @FXML
        void btn_Add_Action(ActionEvent event) {




            getInstance.addstaff(new Staff(
                    txt_Staffid.getText(),
                    txt_email.getText(),
                    txt_name.getText(),
                    txt_phoneNumber.getText()
            ));

        }






        @FXML
        void btn_Search_Action(ActionEvent event) throws SQLException {
          Staff  staff = getInstance.SearchStaff (txt_Staffid.getText());

            if (staff!= null) {
                txt_email.setText(String.valueOf(staff.getEmail()));
                txt_name.setText(String.valueOf(staff.getName()));
                txt_phoneNumber.setText(staff.getPhoneNumber());

            }



        }

        @FXML
        void btn_View_Action(ActionEvent event) {


        }

        @FXML
        void btn_delete_Action(ActionEvent event) {
            Connection connection= DBConnection.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement=connection.prepareStatement("Delete from staff  where staff_id =?");

                preparedStatement.setString(1,txt_Staffid.getText());

                if (0 < preparedStatement.executeUpdate()){
                    new Alert(Alert.AlertType.INFORMATION,"Sucsse Fully Delete").show();

                }else {
                    new  Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full Delete").show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public void loadtable(){
            List<Staff> staff=getInstance.getAll();
            System.out.println(staff);
            ObservableList<Staff> observableList= FXCollections.observableArrayList();
            staff.forEach(stafff -> observableList.add(stafff));
            table_Staff.setItems(observableList);
        }

    public void btn_update_Actiom(ActionEvent actionEvent) {
        boolean b= staffController.UpdateBooks(new Staff(
                txt_Staffid.getText(),
                txt_email.getText(),
                txt_name.getText(),
                txt_phoneNumber.getText()

        ));

    }

    public void btn_Back_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) Ancor_Staff.getScene().getWindow();
        stage.close();
        stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
    }
    public void LodeID(){
        try {
            txt_Staffid.setText(iDgenrateController.generateID("S","staff","staff_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colum_Staffid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        colum_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        colum_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        colum_PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));

        loadtable();
        LodeID();
    }

    public void btn_refesh_Action(ActionEvent actionEvent) {
        loadtable();

    }
}


