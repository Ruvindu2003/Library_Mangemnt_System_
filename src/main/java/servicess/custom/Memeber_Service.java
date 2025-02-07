package servicess.custom;

import model.Members;
import servicess.Super_Service;

import java.util.List;

public interface Memeber_Service extends Super_Service {
    List<Members> getAll();
    Members searchMember(String members);
    boolean updateMemeber(Members members);
    boolean deleteMemeber(Members member_id);
    boolean  AddMemeber(Members members);
}
