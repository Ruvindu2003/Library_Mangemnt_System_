package Services;

import Modlle.Brrowedbooks;
import Modlle.Members;

import java.util.List;

public interface BroowedBooks_Service {
    boolean addBooks (Brrowedbooks brrowedbooks);
    List<Brrowedbooks> getAll();
    public Brrowedbooks searchMember(String browdbooks);




}
