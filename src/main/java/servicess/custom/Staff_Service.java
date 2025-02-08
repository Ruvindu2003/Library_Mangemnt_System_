package servicess.custom;

import entity.Staffentity;
import model.Members;
import model.Staff;
import servicess.Super_Service;

import java.sql.SQLException;
import java.util.List;

public interface Staff_Service extends Super_Service {
    List<Staff> getAll();
    Staff SearchStaff(String staff) throws SQLException;
    boolean UpdateBooks(Staffentity staff);
    boolean DeleteBooks(Staff staffid);
    boolean  addstaff (Staff staff);
}

