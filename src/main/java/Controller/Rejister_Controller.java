package Controller;

import Forms.DBConnection;
import Modlle.Login;
import Services.Rejister_Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rejister_Controller implements Rejister_Service {
    public static Rejister_Controller instance;

    private  Rejister_Controller(){

    }
    public static  Rejister_Controller getInstance(){
        return instance==null ? instance=new Rejister_Controller():instance;
    }


    @Override
    public List<Login> getAlluser() throws SQLException {

        List<Login>logins=new ArrayList<>();
        ResultSet resultSet= (ResultSet) DBConnection.getInstance().getConnection().createStatement();
        while (resultSet.next()){
            boolean add=logins.add(new Login(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));

        }


        return logins;
    }

    @Override
    public Boolean addUser(Login login) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO users Values(?,?,?,?)");
            preparedStatement.setString(1,login.getId());
            preparedStatement.setString(2,login.getUserName());
            preparedStatement.setString(3,login.getEmail());
            preparedStatement.setString(4,login.getPassword());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
