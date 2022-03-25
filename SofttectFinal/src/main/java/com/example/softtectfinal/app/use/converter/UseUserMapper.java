package com.example.softtectfinal.app.use.converter;

import com.example.softtectfinal.app.use.dto.UseUserDto;
import com.example.softtectfinal.app.use.dto.UseUserSaveRequestDto;
import com.example.softtectfinal.app.use.entity.UseUser;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class UseUserMapper {

    UseUserMapper INSTANCE = Mappers.getMapper(UseUserMapper.class);

    UseUser convertToUseUser(UseUserSaveRequestDto useUserSaveRequestDto);

    UseUser convertToUseUser(UseUSerUpdateRequestDto useUSerUpdateRequestDto);

    List<UseUserDto> convertToUseUserDtoList(List<UseUser> useUserList);

    UseUserDto convertToUseUserDto(UseUser cusCustomer);
}
