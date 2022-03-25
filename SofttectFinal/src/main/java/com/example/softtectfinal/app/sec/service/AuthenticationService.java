package com.example.softtectfinal.app.sec.service;

import com.example.softtectfinal.app.sec.dto.SecLoginRequestDto;
import com.example.softtectfinal.app.sec.enums.EnumJwtConstant;
import com.example.softtectfinal.app.sec.security.JwtTokenGenerator;
import com.example.softtectfinal.app.sec.security.JwtUserDetails;
import com.example.softtectfinal.app.use.dto.UseUserDto;
import com.example.softtectfinal.app.use.dto.UseUserSaveRequestDto;
import com.example.softtectfinal.app.use.entity.UseUser;
import com.example.softtectfinal.app.use.service.UseUserService;
import com.example.softtectfinal.app.use.service.entityService.UseUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UseUserService useUserService;
    private final UseUserEntityService useUserEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UseUserDto register(UseUserSaveRequestDto useUserSaveRequestDto) {

        UseUserDto useUserDto = useUserService.save(useUserSaveRequestDto);

        return useUserDto;
    }

    public String login(SecLoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getIdentityNo().toString(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    public UseUser getCurrentCustomer() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        UseUser cusCustomer = null;
        if (jwtUserDetails != null){
            cusCustomer = useUserEntityService.getByIdWithControl(jwtUserDetails.getId());
        }

        return cusCustomer;
    }

    public Long getCurrentCustomerId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
