package Controller;

import db.DBConnection;
import model.Login;
import Services.Loginservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Controller implements Loginservices {
    public static  Login_Controller instance;
    public  static Login_Controller getInstance(){


        return  instance==null? instance=new Login_Controller():instance;
    }






    @Override
    public Login getUser(String user_name, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        preparedStatement.setString(1,user_name);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            Login login=new Login(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)


            );
            System.out.println(login);
            return login;

        }


        return null;
    }
}
