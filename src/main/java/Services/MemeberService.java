package Services;

import Modlle.Members;

import java.util.List;

public interface MemeberService {
    List<Members> getAll();

    Members searchMember(String members);

    boolean updateMemeber(Members members);

    boolean deleteMemeber(Members member_id);

    void  AddMemeber(Members members);

}
