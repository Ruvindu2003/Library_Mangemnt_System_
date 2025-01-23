package Forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public  static  DBConnection instance;
    private Connection connection;


    private  DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb",
                    "root",
                    "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return  connection;
    }
    public static  DBConnection getInstance(){
        return  instance == null ? instance=new DBConnection() :instance;
    }
}



