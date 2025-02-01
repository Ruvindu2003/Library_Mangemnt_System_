package Controller;

import Forms.DBConnection;
import Modlle.Members;
import Services.MemeberService;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;


public class MemberController implements MemeberService {
    private  static  MemberController instance;
    public static  MemberController getInstance(){
        if (instance==null){
            return  instance=new MemberController();
        }
        return instance;
    }

    Connection connection= DBConnection.getInstance().getConnection();


    @Override
    public ArrayList<Members> getAll() {
        ArrayList<Members> members = new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from members");

            while (resultSet.next()){
                members.add(
                        new Members(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)

                        )
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    @Override
    public Members searchMember(String members) {
        Members searchMemberObject = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM members WHERE member_id = ?");
            preparedStatement.setString(1, members);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {


              return new Members(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
               // return searchMemberObject;

               // return null;
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full").show();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public boolean updateMemeber(Members members) {
        try {
            PreparedStatement pst=connection.prepareStatement("UPDATE members SET full_name = ?,phone_number=?,address=?,membership_date=? WHERE member_id=?");

            pst.setString(1, members.getName());
            pst.setString(2,members.getPhoneNumber());
            pst.setString(3,members.getAdrees());
            pst.setString(4,members.getMembershipdates());
            pst.setString(5,members.getMemberid());



            // Executing the update and checking if it succeeded
            return pst.executeUpdate()>0;
        } catch (SQLException e) {
            new  Alert(Alert.AlertType.CONFIRMATION,"Suchses not").show();
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean deleteMemeber(Members member_id) {


        return false;
    }

    @Override
    public void AddMemeber(Members members) {

        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO members VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,members.getMemberid());
            preparedStatement.setString(2,members.getName());
            preparedStatement.setString(3,members.getPhoneNumber());
            preparedStatement.setString(4,members.getAdrees());
            preparedStatement.setString(5,members.getMembershipdates());
            if (preparedStatement.executeUpdate()>0)new Alert(Alert.AlertType.INFORMATION,"Sucses Full ADD").show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
