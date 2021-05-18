package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.modelDto.UserDto;
import com.example.demo.security.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto createNew(UserDto newUser);

    UserDto getOne(long id);

    void delete(long id);

    UserDto update(UserDto updateUser);

    UserDetails findByToken(String token);

    UserDetails register(UserDto userDto);

    AuthResponse login(String username, String password);

    void logout(String username);
}
