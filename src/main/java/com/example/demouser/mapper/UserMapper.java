package com.example.demouser.mapper;

import com.example.demouser.dto.UserDto;
import com.example.demouser.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = PhoneMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INST = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", source = "password", ignore = true)
    UserDto userEntityToUserDto(Users user);

    @Mapping(source = "password", target = "password")
    Users userDtoToUserEntity(UserDto userDto);
}
