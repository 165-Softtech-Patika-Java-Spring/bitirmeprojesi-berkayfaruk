package com.example.softtectfinal.app.use.converter;

import com.example.softtectfinal.app.use.dto.UseUserDto;
import com.example.softtectfinal.app.use.entity.UseUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UseUserConverter {

    public List<UseUserDto> convertToUseUserDtoList(List<UseUser> useUserList) {

        List<UseUserDto> useUserDtoList = new ArrayList<>();
        for (UseUser useUser : useUserList) {

            UseUserDto useUserDto = convertToUseUserDto(useUser);

            useUserDtoList.add(useUserDto);
        }

        return useUserDtoList;
    }

    public UseUserDto convertToUseUserDto(UseUser useUser) {
        UseUserDto useUserDto = new UseUserDto();
        useUserDto.setId(useUser.getId());
        useUserDto.setName(useUser.getName());
        useUserDto.setSurname(useUser.getSurname());
        useUserDto.setIdentityNo(useUser.getIdentityNo());
        return useUserDto;
    }
}
