package Controller;

import db.DBConnection;
import model.Book;
import Services.Book_Service;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book_Controller implements Book_Service {

    private  static  Book_Service instancce;

    public   Book_Service  getInstancce(){
        if (instancce==null){
            return instancce=new Book_Controller();
        }
        return instancce;
    }
    Connection connection= DBConnection.getInstance().getConnection();

    @Override
    public List<Book> getAll() {
        ArrayList<Book> books=new ArrayList<>();
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT *FROM books");
            while (resultSet.next()){
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
    public Book SearcBooks(String books) {
        Book searcbookobject = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE BookID=?");
            preparedStatement.setString(1,books ); // Fix incorrect variable name

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
    public boolean UpdateBooks(Book book) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(" UPDATE books SET Title = ?, Author = ?, ISBN = ?, Language = ? WHERE BookID = ?");

            preparedStatement.setString(1, book.getTiitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setString(4, book.getLanvage());
            preparedStatement.setString(5, book.getBookid());
            new Alert(Alert.AlertType.INFORMATION,"Sucsse FUlly").show();
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Sucsses Full").show();
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean DeleteBooks(Book bookid) {
        return false;
    }

    @Override
    public void Addbook(Book book) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,book.getBookid());
            preparedStatement.setString(2,book.getTiitle());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setString(4,book.getIsbn());
            preparedStatement.setString(5,book.getLanvage());

            if (preparedStatement.executeUpdate()>0)
                new Alert(Alert.AlertType.INFORMATION,"Success Full").show();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
