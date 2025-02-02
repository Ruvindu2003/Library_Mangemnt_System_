package Controller;

import Forms.DBConnection;
import Modlle.Brrowedbooks;
import Modlle.Members;
import Services.BroowedBooks_Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrrowedController implements BroowedBooks_Service {
    public  static BrrowedController instance;

    public BrrowedController getInstance(){
        if (instance==null){
            instance=new BrrowedController();
        }
        return instance;
    }



    @Override
    public boolean addBooks(Brrowedbooks brrowedbooks) {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO borrowed_books (borrowed_id, book_id, member_id, staff_id, borrow_date, return_date, returned) VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1,brrowedbooks.getBrrowedbokksid());
            preparedStatement.setString(2,brrowedbooks.getBookID());
            preparedStatement.setString(3,brrowedbooks.getMemberid());
            preparedStatement.setString(4,brrowedbooks.getStaffid());
            preparedStatement.setString(5,brrowedbooks.getBrooedate());
            preparedStatement.setString(6,brrowedbooks.getIsBrowwed());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<Brrowedbooks> getAll() {
        Connection connection=DBConnection.getInstance().getConnection();

        ArrayList<Brrowedbooks> brrowedbooks= new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(" Select * from borrowedbooks");

            while (resultSet.next()){
                brrowedbooks.add(
                        new Brrowedbooks(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brrowedbooks;

    }
}
