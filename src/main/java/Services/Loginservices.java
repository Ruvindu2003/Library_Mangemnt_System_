package Services;

import Modlle.Login;

import java.sql.SQLException;

public interface Loginservices {
Login getUser(String user_name ,String password) throws SQLException;
}
