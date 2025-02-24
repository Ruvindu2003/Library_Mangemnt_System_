package Controller;


import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDgenrateController {
    public  static  IDgenrateController instance;

    private IDgenrateController(){

    }
    public static IDgenrateController getInstance(){
        if (instance==null){
            instance=new IDgenrateController();
        }
        return instance;
    }
    Connection connection=DBConnection.getInstance().getConnection();

    public String generateID(String letter, String tableName,String idColumn) throws SQLException {
        String query = "SELECT "+idColumn+" FROM " + tableName + " WHERE "+idColumn+" LIKE ? ORDER BY "+idColumn+" DESC LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, letter + "%");
            ResultSet resultSet = statement.executeQuery();

            int newNumber = 1;
            if (resultSet.next()) {
                String lastId = resultSet.getString(idColumn);
                int lastNumber = Integer.parseInt(lastId.substring(2));
                newNumber = lastNumber + 1;
            }

            return String.format("%s%03d", letter, newNumber);
        }
    }
}
