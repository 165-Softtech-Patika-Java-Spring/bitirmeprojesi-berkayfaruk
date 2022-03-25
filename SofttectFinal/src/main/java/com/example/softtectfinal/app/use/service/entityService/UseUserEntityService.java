package com.example.softtectfinal.app.use.service.entityService;

import com.example.softtectfinal.app.use.dao.UseUserDao;
import com.example.softtectfinal.app.use.entity.UseUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UseUserEntityService {

    public UseUserEntityService(UseUserDao dao) {
        super(dao);
    }

    public List<UseUser> findAllBySurname(String surname){
        return getDao().findAllBySurname(surname);
    }

    public UseUser findByIdentityNo(Long identityNo){
        return getDao().findByIdentityNo(identityNo);
    }
}
