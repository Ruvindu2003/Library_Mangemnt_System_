package Forms;

import Controller.Login_Controller;
import javafx.scene.layout.AnchorPane;
import model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Login_Form {
    public TextField txt_username;
    public TextField txt_password;
    public AnchorPane ancor_Login;


    public void Rejister_on_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Rejister.fxml"))));
        stage.setTitle("Rejister");
        stage.show();
    }



    public void btn_Login_Action(ActionEvent actionEvent) throws SQLException {
        Login User= Login_Controller.getInstance().getUser(txt_username.getText(),txt_password.getText());
        System.out.println(User);

        if (User==null){
            new  Alert(Alert.AlertType.INFORMATION,"Login Fail").show();

            return;
        }else {

        }

        Stage stage=(Stage)ancor_Login.getScene().getWindow();
        stage.close();
        stage = new Stage();

        try {
            new  Alert(Alert.AlertType.INFORMATION,"Success Full").show();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
            stage.setTitle("DashBoard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void btn_Forgot_Password(ActionEvent actionEvent) {
        Stage stage=(Stage) ancor_Login.getScene().getWindow();
        stage.close();
        stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Forgotpassword.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Password");

    }
}
