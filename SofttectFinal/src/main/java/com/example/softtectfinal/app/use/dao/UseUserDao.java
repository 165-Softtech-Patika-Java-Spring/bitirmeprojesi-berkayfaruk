package com.example.softtectfinal.app.use.dao;

import com.example.softtectfinal.app.use.entity.UseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UseUserDao extends JpaRepository<UseUser,Long> {
    List<UseUser> findAllBySurname(String surname);

    UseUser findByIdentityNo(Long identityNo);
}
