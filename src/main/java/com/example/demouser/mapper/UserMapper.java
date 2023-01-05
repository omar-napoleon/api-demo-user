package com.example.demouser.mapper;

import com.example.demouser.dto.PhoneDto;
import com.example.demouser.dto.UserRequestDto;
import com.example.demouser.dto.UserResponseDto;
import com.example.demouser.entity.Phone;
import com.example.demouser.entity.User;
import com.example.demouser.enums.Role;
import org.mapstruct.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demouser.utils.Constants.EXPIRATION_TOKEN_SECONDS;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "phones", source = "phones", ignore = true)
    @Mapping(target = "email", source = "email", ignore = true)
    @Mapping(target = "name", source = "name", ignore = true)
    UserResponseDto userEntityToUserDto(User user);

    @Mapping(source = "password", target = "password")
    User userDtoToUserEntity(UserRequestDto userRequestDto);

    @AfterMapping
    default User setUserOnPhones(@MappingTarget User user, String jwtToken, String pass) {
        List<Phone> Phones = user.getPhones().stream().map(userPhone -> {
            userPhone.setUser(user);
            return userPhone;
        }).collect(Collectors.toList());
        user.setActive(true);
        user.setToken(jwtToken);
        user.setRole(Role.USER);
        user.setPassword(pass);
        user.setPhones(Phones);
        user.setTokenExpiration(Instant.now().plusSeconds(EXPIRATION_TOKEN_SECONDS));
        user.setLastLogin(Instant.now());

        return user;
    }

    @AfterMapping
    default UserResponseDto setUserOnPhonesDto(@MappingTarget UserResponseDto userDto, User user) {
        List<PhoneDto> phonesDto = user.getPhones().stream().map(phone -> {
            var phonesDtoAux = PhoneDto.builder()
                    .number(phone.getNumber())
                    .cityCode(phone.getCityCode())
                    .countryCode(phone.getCountryCode())
                    .build();
            return phonesDtoAux;
        }).collect(Collectors.toList());
        userDto.setToken(null);
        userDto.setPhones(phonesDto);
        return userDto;
    }
}
