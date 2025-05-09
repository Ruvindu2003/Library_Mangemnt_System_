package Forms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Login;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rejister_Form {
    public TextField txt_id;
    public TextField txt_username;
    public TextField txt_Email;
    public TextField txt_Password;
    public TextField txt_conform_Password;
    public AnchorPane ancorpain_Back_Action;

    public void btn_Action_Action(ActionEvent actionEvent) {
        String key ="12345";

        Login basicTextEncryptor = new Login();
        basicTextEncryptor.setPassword(key);

        if (txt_Password.getText().equals(txt_conform_Password.getText())){
            System.out.println(true);
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = null;
            try {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email=" + "'" + txt_Email.getText() + "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (!resultSet.next()){
                    System.out.println(false);

                    String SQL = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
                    PreparedStatement psTm = connection.prepareStatement(SQL);
                    psTm.setString(1,txt_username.getText());
                    psTm.setString(2,txt_Email.getText());
                    psTm.setString(3,txt_Password.getText());
                    psTm.executeUpdate();


                }else{
                    new Alert(Alert.AlertType.INFORMATION,"Success Full Register").show();

                    System.out.println(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            new Alert(Alert.AlertType.INFORMATION," Not Success Register").show();

            System.out.println(false);
        }


    }

    public void btn_Back_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)ancorpain_Back_Action.getScene().getWindow();
        stage.close();
        stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml"))));
        stage.setTitle("Login");
        stage.show();

    }
}











