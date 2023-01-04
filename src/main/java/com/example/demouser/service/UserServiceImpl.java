package com.example.demouser.service;

import com.example.demouser.dto.UserDto;
import com.example.demouser.entity.User;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User userEntity = userMapper.userDtoToUserEntity(userDto);
        User resp = userRepository.save(userEntity);
        return userMapper.userEntityToUserDto(resp);
    }

}
