package com.example.demo.service;

import com.example.demo.modelDto.UserDto;
import com.example.demo.modelDto.UserGetDto;
import com.example.demo.security.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserGetDto> getAll();

    UserDto createNew(UserDto newUser);

    UserGetDto getOne(long id);

    void delete(long id);

    UserGetDto update(UserGetDto updateGetUser);

    UserDetails findByToken(String token);

    UserDetails register(UserDto userDto);

    AuthResponse login(String username, String password);

    void logout(String username);
}
