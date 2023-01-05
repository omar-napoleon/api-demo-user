package com.example.demouser.mapper;

import com.example.demouser.dto.UserDto;
import com.example.demouser.entity.Phone;
import com.example.demouser.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = PhoneMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", source = "password", ignore = true)
    @Mapping(target = "phones", source = "phones", ignore = true)
    @Mapping(target = "email", source = "email", ignore = true)
    @Mapping(target = "name", source = "name", ignore = true)
    UserDto userEntityToUserDto(User user);

    @Mapping(source = "password", target = "password")
    User userDtoToUserEntity(UserDto userDto);

    @AfterMapping
    default User setUserOnPhones(@MappingTarget User user) {
        List<Phone> Phones = user.getPhones().stream().map(userPhone -> {
            userPhone.setUser(user);
            return userPhone;
        }).collect(Collectors.toList());
        user.setActive(true);
        user.setPhones(Phones);
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiration(Instant.now().plusSeconds(120));
        user.setLastLogin(Instant.now());

        return user;
    }
}
