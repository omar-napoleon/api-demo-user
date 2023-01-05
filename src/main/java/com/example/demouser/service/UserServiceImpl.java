package com.example.demouser.service;

import com.example.demouser.dto.AuthenticationRequestDto;
import com.example.demouser.dto.AuthenticationResponseDto;
import com.example.demouser.dto.UserRequestDto;
import com.example.demouser.dto.UserResponseDto;
import com.example.demouser.exception.CustomException;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.repository.UserRepository;
import com.example.demouser.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;
import java.util.UUID;

import static com.example.demouser.utils.Constants.EXPIRATION_TOKEN_SECONDS;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws Exception {
        existEmail(userRequestDto.getEmail());
        var user = userMapper.userDtoToUserEntity(userRequestDto);
        var jwtToken = jwtService.generateToken(user);
        user = userMapper.setUserOnPhones(user, jwtToken, passwordEncoder.encode(user.getPassword()));
        var resp = userRepository.save(user);
        return userMapper.userEntityToUserDto(resp);
    }

    @Override
    public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        user.setModifiedAt(Instant.now());
        user.setTokenExpiration(Instant.now().plusSeconds(EXPIRATION_TOKEN_SECONDS));
        userRepository.save(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public UserResponseDto getUserById(String id) {
        var user = userRepository.findById(UUID. fromString(id))
                .orElseThrow();
        var resp = userMapper.userEntityToUserDto(user);
        resp = userMapper.setUserOnPhonesDto(resp, user);
        return resp;
    }

    private void existEmail(String email) throws Exception {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new CustomException(messageSource.getMessage(
                    "response.userExist", null, Locale.ENGLISH), HttpStatus.FORBIDDEN);
        }
    }

}
