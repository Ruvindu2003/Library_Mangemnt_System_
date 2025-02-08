package servicess.custom;

import entity.Login_entity;
import servicess.Super_Service;

import java.sql.SQLException;

public interface Login_Service extends Super_Service {
    Login_entity getUser(String user_name , String password) throws SQLException;
}
