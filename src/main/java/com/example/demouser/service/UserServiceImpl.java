package com.example.demouser.service;

import com.example.demouser.configuration.MessageConfig;
import com.example.demouser.dto.UserDto;
import com.example.demouser.entity.User;
import com.example.demouser.exception.CustomException;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, MessageSource messageSource) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        existEmail(userDto.getEmail());
        User resp = userRepository.save(userMapper.userDtoToUserEntity(userDto));
        return userMapper.userEntityToUserDto(resp);
    }

    private void existEmail(String email) throws Exception {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new CustomException(messageSource.getMessage(
                    "response.userExist", null, Locale.ENGLISH), HttpStatus.FORBIDDEN);
        }
    }

}
