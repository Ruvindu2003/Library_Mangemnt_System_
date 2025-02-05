package Controller;

import Forms.DBConnection;
import Modlle.Return_Book;
import Services.Return_Books_Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnBooks_Controoler implements Return_Books_Service {
    public static  ReturnBooks_Controoler instance;

    public  ReturnBooks_Controoler getInstance( ){

        if (instance==null){
            instance=new ReturnBooks_Controoler();
        }


      return instance;
    }

    Connection connection =DBConnection.getInstance().getConnection();


    @Override
    public boolean addBooks(Return_Book returnBook) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO returnsbooks VALUES(?,?,?,?,?,?)");

            preparedStatement.setString(1,returnBook.getReturnid());
            preparedStatement.setString(2,returnBook.getBookid());
            preparedStatement.setString(3,returnBook.getBookid());
            preparedStatement.setString(4,returnBook.getReturn_date());
            preparedStatement.setString (5, String.valueOf(Double.parseDouble(String.valueOf(returnBook.getFindAmount()))));
            preparedStatement.setString(6,returnBook.getReturn_status());


            if (preparedStatement.executeUpdate()>0){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

