package com.example.demouser.service;

import com.example.demouser.dto.UserDto;
import com.example.demouser.entity.Users;
import com.example.demouser.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

//    @Autowired
//    public UserServiceImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public UserDto createUser(UserDto userDto) {

        Users usersEntity = userMapper.userDtoToUserEntity(userDto);
        return null;
    }

}
