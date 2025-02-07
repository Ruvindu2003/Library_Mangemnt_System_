package repository.custom.impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Book;
import model.Members;
import model.Staff;
import repository.custom.Book_Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Book_Repository_impl implements Book_Repository {



    @Override
    public Book add(Book entity) {
        try {
            PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO books VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,entity.getBookid());
            preparedStatement.setString(2,entity.getTiitle());
            preparedStatement.setString(3,entity.getAuthor());
            preparedStatement.setString(4,entity.getIsbn());
            preparedStatement.setString(5,entity.getLanvage());

            if (preparedStatement.executeUpdate()>0) new Alert(Alert.AlertType.INFORMATION,"Sucsess Full").show();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    return  entity;
}





    @Override
    public boolean update(Book entity) {
        try {
            PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement(" UPDATE books SET Title = ?, Author = ?, ISBN = ?, Language = ? WHERE BookID = ?");

            preparedStatement.setString(1, entity.getTiitle());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getIsbn());
            preparedStatement.setString(4, entity.getLanvage());
            preparedStatement.setString(5, entity.getBookid());
            new Alert(Alert.AlertType.INFORMATION,"Sucsse FUlly").show();
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Sucsses Full").show();
            throw new RuntimeException(e);
        }




    }

    @Override
    public boolean delete(String id) {


        return false;
    }

    @Override
    public Members Search(String entity) {
        return  null;
    }


    @Override
    public ArrayList<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *FROM books");
            while (resultSet.next()) {
                books.add(
                        new Book(
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

        return books;


    }

    @Override
    public Book SerachBook(String entity) {
        Book searcbookobject = null;

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM books WHERE BookID=?");
            preparedStatement.setString(1, entity); // Fix incorrect variable name

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
            }

            // Corrected: Fetch data after ensuring a result exists
            searcbookobject = new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return searcbookobject;
    }

    @Override
    public List<Members> getAl() {
        return List.of();
    }

    @Override
    public Members addMemeber(Book entity) {


        return null;
    }

    @Override
    public entity.Staff addStaff(Book entity) {
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
}
