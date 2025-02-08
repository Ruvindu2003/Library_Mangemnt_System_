package repository.custom.impl;

import db.DBConnection;
import entity.Login_entity;
import javafx.scene.control.Alert;
import model.Login;
import model.Members;
import model.Book;
import model.Staff;
import repository.custom.Member_Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Member_Repository_impl implements Member_Repository {


    @Override
    public Book add(Members entity) {
        return null;
    }

    @Override
    public boolean update(Members entity) {
        try {
            PreparedStatement pst=DBConnection.getInstance().getConnection().prepareStatement("UPDATE members SET full_name = ?,phone_number=?,address=?,membership_date=? WHERE member_id=?");

            pst.setString(1, entity.getName());
            pst.setString(2,entity.getPhoneNumber());
            pst.setString(3,entity.getAdrees());
            pst.setString(4,entity.getMembershipdates());
            pst.setString(5,entity.getMemberid());

                     return pst.executeUpdate()>0;

        } catch (SQLException e) {
            new  Alert(Alert.AlertType.CONFIRMATION,"Suchses not").show();
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Members Search(String entity) {
        Members searchMemberObject = null;

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM members WHERE member_id = ?");
            preparedStatement.setString(1, entity);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

            }
                searchMemberObject = new Members(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return searchMemberObject;
    }

    @Override
    public List<Book> getAll() {return List.of();}

    @Override public Book SerachBook(String entity) {return null;}

    @Override
    public List<Members> getAl() {
        ArrayList<Members> members = new ArrayList<>();
        try {
            Statement statement=DBConnection.getInstance().getConnection().createStatement();
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
    public Members addMemeber(Members entity) {
        try {
            PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO members VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,entity.getMemberid());
            preparedStatement.setString(2,entity.getName());
            preparedStatement.setString(3,entity.getPhoneNumber());
            preparedStatement.setString(4,entity.getAdrees());
            preparedStatement.setString(5,entity.getMembershipdates());
            if (preparedStatement.executeUpdate()>0)new Alert(Alert.AlertType.INFORMATION,"Sucses Full ADD").show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    return entity;
    }

    @Override public Staff addStaff(Staff entity) {
        return null;
    }
    @Override public Staff searchStaff(String entity) {
        return null;
    }
    @Override public List<Staff> getAllStaff() {
        return List.of();
    }

    @Override
    public Login_entity getUser(String user_name, String password) {
        return null;
    }

}


