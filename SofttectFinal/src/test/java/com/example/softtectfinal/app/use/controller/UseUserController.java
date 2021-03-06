package com.example.softtectfinal.app.use.controller;

import com.example.softtectfinal.app.use.dto.UseUserDto;
import com.example.softtectfinal.app.use.dto.UseUserSaveRequestDto;
import com.example.softtectfinal.app.use.dto.UseUserUpdateRequestDto;
import com.example.softtectfinal.app.use.entity.UseUser;
import com.example.softtectfinal.app.use.service.UseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UseUserController {
    private final UseUserService useUserService;

    @Operation(tags = "User Controller", description = "Gets all customer.", summary = "All user")
    @GetMapping
    public ResponseEntity findAll(){

        List<UseUserDto> cusCustomerDtoList = useUserService.findAll();

        return ResponseEntity.ok(RestResponse.of(useUSerDtoList));
    }

    @Operation(tags = "User Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        UseUserDto useUserDto = cusCustomerService.findById(id);

        return ResponseEntity.ok(RestResponse.of(useUserDto));
    }

    @Operation(
            tags = "User Controller",
            description = "Creates new user",
            summary = "creates new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CusCustomerSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new foreign customer",
                                                    summary = "New Foreign Customer Example",
                                                    description = "Complete request with all available fields for foreign customer",
                                                    value = "{\n" +
                                                            "  \"name\": \"john\",\n" +
                                                            "  \"surname\": \"smith\",\n" +
                                                            "  \"identityNo\": 92345678901,\n" +
                                                            "  \"password\": \"J.s_1234\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "new customer",
                                                    summary = "New Customer Example",
                                                    description = "Complete request with all available fields",
                                                    value = "{\n" +
                                                            "  \"name\": \"ali\",\n" +
                                                            "  \"surname\": \"veli\",\n" +
                                                            "  \"identityNo\": 12345678901,\n" +
                                                            "  \"password\": \"A.v_1234\"\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PostMapping
    public ResponseEntity save(@RequestBody UseUserSaveRequestDto useUserSaveRequestDto){

        UseUserDto cusCustomerDto = useUserService.save(useUserSaveRequestDto);

        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).findById(cusCustomerDto.getId()));

        WebMvcLinkBuilder linkDelete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).delete(cusCustomerDto.getId()));

        EntityModel entityModel = EntityModel.of(cusCustomerDto);

        entityModel.add(linkGet.withRel("find-by-id"));
        entityModel.add(linkDelete.withRel("delete"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        ProcessHandle RestResponse;
        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    @Operation(tags = "User Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        useUserService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "User Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody UseUserUpdateRequestDto useUserUpdateRequestDto){

        UseUserDto useUserDto = useUserService.update(new UseUserUpdateRequestDto());

        return ResponseEntity.ok(RestResponse.of(useUserDto));
    }
}
