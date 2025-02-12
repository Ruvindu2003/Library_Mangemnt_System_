package Controller;

import db.DBConnection;
import model.Staff;
import Services.Staff_Service;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffController implements Staff_Service {

    private  static  StaffController instance;
    public  StaffController getInstance(){
        if (instance==null){
            return instance=new StaffController();
        }
        return instance;
    }
    Connection connection= DBConnection.getInstance().getConnection();
    @Override
    public List<Staff> getAll() {
        ArrayList<Staff> staff=new ArrayList<>();
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT *FROM staff");
            while (resultSet.next()){
                staff.add(
                        new Staff(
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
    public Staff SearchStaff(String staff) throws SQLException {
        Staff searcStaff = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE staff_id=?");
            preparedStatement.setString(1,staff ); // Fix incorrect variable name

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {  // If no book is found, return null
                return null;
            }

            searcStaff=new Staff(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return searcStaff;

    }

    @Override
    public boolean UpdateBooks(Staff staff) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE staff SET email=?,name =?,phone_number=?WHERE staff_id=?");

            preparedStatement.setString(1, staff.getStaffid());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getName());
            preparedStatement.setString(4, staff.getPhoneNumber());

            new Alert(Alert.AlertType.INFORMATION,"Success FAlly").show();
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Successes Full").show();
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean DeleteBooks(Staff staffid) {
        return false;
    }

    @Override
    public void Addbook(Staff staff) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO staff VALUES(?,?,?,?)");

            preparedStatement.setString(1,staff.getStaffid());
            preparedStatement.setString(2,staff.getEmail());
            preparedStatement.setString(3,staff.getName());
            preparedStatement.setString(4,staff.getPhoneNumber());


            if (preparedStatement.executeUpdate()>0) new Alert(Alert.AlertType.INFORMATION,"Success Full").show();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
