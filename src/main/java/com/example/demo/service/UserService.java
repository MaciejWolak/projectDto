package com.example.demo.service;

import com.example.demo.modelDto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto createNew(UserDto newUser);

    UserDto getOne(long id);

    void delete(long id);

    UserDto update(UserDto updateUser);


}
