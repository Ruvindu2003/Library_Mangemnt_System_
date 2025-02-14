package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoard_Form {

    public AnchorPane Ancopain_dashbord;

    public void btn_member_Action(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Member.fxml"))));
        stage.setTitle("Member Form");
        stage.show();
    }

    public void btn_Addbooks_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Add_books.fxml"))));
        stage.setTitle("Add Form");
        stage.show();
    }

    public void btn_Staff_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Staff_Form.fxml"))));
        stage.show();

    }

    public void Borrowed_Bokkss_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Take_Books.fxml"))));
        stage.setTitle("Borrowed Books");

        stage.show();
    }

    public void btn_Return_book_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ReturnBooks.fxml"))));
        stage.setTitle("Return Books");
        stage.show();
    }

    public void btn_Action_Logout(ActionEvent actionEvent) {
        Stage stage = (Stage) Ancopain_dashbord.getScene().getWindow();
        stage.close();
        stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Login Form");






    }

    public void btn_Report_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Report.fxml"))));
        stage.show();
        stage.setTitle("Report Form");
    }
}
