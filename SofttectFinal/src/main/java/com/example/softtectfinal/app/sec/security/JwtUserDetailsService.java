package com.example.softtectfinal.app.sec.security;

import com.example.softtectfinal.app.use.entity.UseUser;
import com.example.softtectfinal.app.use.service.entityService.UseUserEntityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JwtUserDetailsService {
    private final UseUserEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo = Long.valueOf(username);

        UseUser useUser = cusCustomerEntityService.findByIdentityNo(identityNo);

        return JwtUserDetails.create(useUser);
    }

    public UserDetails loadUserByUserId(Long id) {

        UseUser useUser = cusCustomerEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(useUser);
    }
}
