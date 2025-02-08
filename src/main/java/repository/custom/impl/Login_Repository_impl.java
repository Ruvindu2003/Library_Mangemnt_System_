package repository.custom.impl;

import db.DBConnection;
import entity.Login_entity;
import model.Book;
import model.Login;
import model.Members;
import model.Staff;
import repository.custom.Login_Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Login_Repository_impl implements Login_Repository {


    @Override
    public Book add(Login_entity entity) {
        return null;
    }

    @Override
    public boolean update(Login_entity entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Members Search(String entity) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public Book SerachBook(String entity) {
        return null;
    }

    @Override
    public List<Members> getAl() {
        return List.of();
    }

    @Override
    public Members addMemeber(Login_entity entity) {
        return null;
    }

    @Override
    public Staff addStaff(Staff entity) {
        return null;
    }

    @Override
    public Staff searchStaff(String entity) {
        return null;
    }

    @Override
    public List<Staff> getAllStaff() {
        return List.of();
    }

    @Override
    public Login_entity getUser(String user_name, String password) throws SQLException {

        PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        preparedStatement.setString(1,user_name);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            Login_entity login=new Login_entity(
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


