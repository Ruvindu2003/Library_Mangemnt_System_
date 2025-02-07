package servicess.custom.impl;

import model.Members;
import repository.Repository_Factory;
import repository.custom.Member_Repository;
import servicess.custom.Memeber_Service;
import utill.RepositoryType;

import java.util.List;

public class Memeber_Servic_impl implements Memeber_Service {
        Member_Repository  memberRepository= Repository_Factory.getInstance().getRepository_Factory(RepositoryType.Member);

    @Override
    public List<Members> getAll() {
        List<Members>all=memberRepository.getAl();


      return  all;
    }

    @Override
    public Members searchMember(String members) {
        Members searchMemebers =memberRepository.Search(members);
        return  searchMemebers;
    }

    @Override
    public boolean updateMemeber(Members members) {
        boolean updatamemeber=memberRepository.update(members);
        return updatamemeber;
    }

    @Override
    public boolean deleteMemeber(Members member_id) {
        return false;
    }

    @Override
    public boolean AddMemeber(Members members) {
        Members add = memberRepository.addMemeber(members);
        if (add != null) {

        } else {
            return false;
        }

    return  false;
    }
}