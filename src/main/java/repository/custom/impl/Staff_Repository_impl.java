package repository.custom.impl;

import db.DBConnection;
import entity.Login_entity;
import entity.Staffentity;
import javafx.scene.control.Alert;
import model.Book;
import model.Login;
import model.Members;
import model.Staff;
import repository.custom.Staff_Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Staff_Repository_impl implements Staff_Repository {
    @Override
    public Book add(Staffentity entity) {
        return null;
    }
    @Override
    public boolean update(Staffentity entity) {
        try {
            PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement("UPDATE staff SET email = ?,name = ?,phone_number=?WHERE staff_id=?");

            preparedStatement.setString(1, entity.getStaffid());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getPhoneNumber());

            new Alert(Alert.AlertType.INFORMATION,"Success FAlly").show();
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Successes Full").show();
            throw new RuntimeException(e);
        }

    }
    @Override public boolean delete(String id) {return false;}
    @Override public Members Search(String entity) {return null;}
    @Override public List<Book> getAll() {return List.of();}
    @Override public Book SerachBook(String entity) {return null;}
    @Override public List<Members> getAl() {return List.of();}
    @Override public Members addMemeber(Staffentity entity) {return null;}

    @Override
    public Staff addStaff(Staff entity) {
        try {
            PreparedStatement preparedStatement=    DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO staff VALUES(?,?,?,?)");

            preparedStatement.setString(1,entity.getStaffid());
            preparedStatement.setString(2,entity.getEmail());
            preparedStatement.setString(3,entity.getName());
            preparedStatement.setString(4,entity.getPhoneNumber());


            if (preparedStatement.executeUpdate()>0) new Alert(Alert.AlertType.INFORMATION,"Success Full").show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
    @Override
    public model.Staff searchStaff(String entity) {
        model.Staff searcStaff = null;

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM staff WHERE staff_id=?");
            preparedStatement.setString(1,entity ); // Fix incorrect variable name

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {  // If no book is found, return null
                return null;
            }

            searcStaff=new model.Staff(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );} catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return searcStaff;

    }
    @Override
    public List<model.Staff> getAllStaff() {
        ArrayList<model.Staff> staff=new ArrayList<>();
        try {
            Statement statement= DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT *FROM staff");
            while (resultSet.next()){
                staff.add(
                        new model.Staff(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)

                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }

    @Override
    public Login_entity getUser(String user_name, String password) {
        return null;
    }
}