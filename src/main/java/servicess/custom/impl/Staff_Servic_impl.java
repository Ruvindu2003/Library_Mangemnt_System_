package servicess.custom.impl;

import model.Staff;
import repository.Repository_Factory;
import repository.custom.Staff_Repository;
import servicess.custom.Staff_Service;
import utill.RepositoryType;

import java.sql.SQLException;
import java.util.List;

import static com.google.common.collect.Range.all;


public class Staff_Servic_impl implements Staff_Service {
    Staff_Repository staffRepository= Repository_Factory.getInstance().getRepository_Factory(RepositoryType.Staff);
    @Override
    public List<Staff> getAll() {
        List<Staff>all=staffRepository.getAllStaff();

        return all;
    }

    @Override
    public Staff SearchStaff(String staff)  {
        Staff  search =staffRepository.searchStaff(staff);

        return  search;
    }

    @Override
    public boolean UpdateBooks(Staff staff) {
        return false;
    }

    @Override
    public boolean DeleteBooks(Staff staffid) {

        return false;
    }

    @Override
    public boolean addstaff(Staff staff) {


        return  false;
}
}
