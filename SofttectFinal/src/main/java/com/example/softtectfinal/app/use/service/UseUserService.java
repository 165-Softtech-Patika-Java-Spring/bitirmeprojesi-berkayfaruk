package com.example.softtectfinal.app.use.service;

import com.example.softtectfinal.app.use.converter.UseUserConverter;
import com.example.softtectfinal.app.use.converter.UseUserMapper;
import com.example.softtectfinal.app.use.dto.UseUserDto;
import com.example.softtectfinal.app.use.dto.UseUserSaveRequestDto;
import com.example.softtectfinal.app.use.dto.UseUserUpdateRequestDto;
import com.example.softtectfinal.app.use.entity.UseUser;
import com.example.softtectfinal.app.use.service.entityService.UseUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UseUserService {

    private final UseUserEntityService useUserEntityService;
    private final UseUserConverter useUserConverter;
    private final PasswordEncoder passwordEncoder;

    public List<UseUserDto> findAll() {

        List<UseUser> useUserList = useUserEntityService.findAll();

        List<UseUserDto> useUserDtoList = useUserConverter.convertToUseUserDtoList(useUserList);

        return useUserDtoList;
    }

    public UseUserDto save(UseUserSaveRequestDto useUserSaveRequestDto) {

        UseUser useUser = UseUserMapper.INSTANCE.convertToCusCustomer(useUserSaveRequestDto);

        String password = passwordEncoder.encode(useUSer.getPassword());
        useUser.setPassword(password);

        useUser = useUserEntityService.save(useUser);

        UseUserDto useUserDto = UseUserMapper.INSTANCE.convertToCusCustomerDto(useUser);

        return useUserDto;
    }


    public void delete(Long id) {

        UseUser useUser = useUserEntityService.getByIdWithControl(id);

        cusCustomerEntityService.delete(cusCustomer);
    }

    public UseUserDto findById(Long id) {

        UseUser useUser = useUserEntityService.getByIdWithControl(id);

        UseUserDto useUserDto = UseUserMapper.INSTANCE.convertToCusCustomerDto(useUser);

        return useUserDto;
    }


    public UseUserDto update(UseUserUpdateRequestDto useUserUpdateRequestDto) {

        controlIsCustomerExist(UseUserUpdateRequestDto);

        UseUser cusCustomer = UseUserMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);
        cusCustomer = useUserEntityService.save(cusCustomer);

        UseUserDto useUserDto = UseUserMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return useUserDto;
    }

    private void controlIsCustomerExist(UseUserUpdateRequestDto useUserUpdateRequestDto) {

        Long id = useUserUpdateRequestDto.getId();
        boolean isExist = useUserEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(CusErrorMessage.CUSTOMER_ERROR_MESSAGE);
        }
    }
}
