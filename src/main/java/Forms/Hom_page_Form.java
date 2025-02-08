package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Hom_page_Form {
    public Button btn_Logout;
    public AnchorPane frame_home;

    public void Lets_Go_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml"))));
        stage.show();
        stage.setTitle("Login Form");


    }

    public void btn_Logout_Action(ActionEvent actionEvent) throws IOException {


    }
}
