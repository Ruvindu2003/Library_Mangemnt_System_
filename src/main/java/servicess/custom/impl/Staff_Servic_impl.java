package servicess.custom.impl;

import entity.Staffentity;
import model.Members;
import model.Staff;
import repository.Repository_Factory;
import repository.custom.Staff_Repository;
import servicess.custom.Staff_Service;
import utill.RepositoryType;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;


public class Staff_Servic_impl implements Staff_Service {
    Staff_Repository staffRepository = Repository_Factory.getInstance().getRepository_Factory(RepositoryType.Staff);


    @Override
    public List<Staff> getAll() {
        List<Staff> all = staffRepository.getAllStaff();
        return all;
    }

    @Override
    public Staff SearchStaff(String staff) throws SQLException {
        Staff search=staffRepository.searchStaff(staff);
        return search;
    }

    @Override
    public boolean UpdateBooks(Staffentity staff) {
        boolean updatebooks = staffRepository.update(staff);
        return updatebooks;
    }

    @Override
    public boolean DeleteBooks(Staff staffid) {
        return false;
    }

    @Override
    public boolean addstaff(Staff staff) {
        Staff add =staffRepository.addStaff(staff);
        if (add !=null){

        }else {

        }
        return false;
    }
}
