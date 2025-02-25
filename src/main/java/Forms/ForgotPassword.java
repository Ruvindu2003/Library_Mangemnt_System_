package Forms;

import db.DBConnection;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgotPassword {
    public TextField txt_Email;
    public Button btn_send;
    public AnchorPane Ancorpain_fogtpassword;
    public TextArea text_Area;
    Connection connection = DBConnection.getInstance().getConnection();

    public void btn_send_Action(ActionEvent actionEvent) {
        String respondsemail = txt_Email.getText();
        try {
            if (checkEmailExists(respondsemail)) {
                String generatedPassword = generateRandomPassword(8);
                updatePasswordInDatabase(respondsemail, generatedPassword);
                sendEmail(respondsemail, generatedPassword);
                new Alert(Alert.AlertType.INFORMATION, "New password sent successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Email not found.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private boolean checkEmailExists(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return password.toString();
    }

    private void sendEmail(String respondsemail, String generatedPassword) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myEmail = "sharadharuvindu@gmail.com";
        String password = "sfad owss ihay ionp"; // Consider using environment variables for security

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        Message message = prepareMessage(session, myEmail, respondsemail, generatedPassword);
        if (message != null) {
            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Request Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to send email").show();
        }
    }

    private Message prepareMessage(Session session, String myEmail, String respondsemail, String generatedPassword) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(respondsemail));
            message.setSubject("Password Reset");
            message.setText("Your new password is: " + generatedPassword);
            return message;
        } catch (Exception e) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private void updatePasswordInDatabase(String email, String newPassword) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
    }

    public void btn_Action_Back(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)Ancorpain_fogtpassword.getScene().getWindow();
        stage.close();
        stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml "))));
        stage.setTitle("Login");
        stage.show();
    }
}