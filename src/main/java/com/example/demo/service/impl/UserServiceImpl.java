package com.example.demo.service.impl;

import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.User;
import com.example.demo.modelDto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Override
    public List<UserDto> getAll() {
        return mapper.usersAll(userRepository.findAll());
    }
    @Override
    public UserDto createNew(UserDto newUser) {
        User user= mapper.userDtoToUser(newUser);
        user = userRepository.save(user);
        return mapper.userToUserDto(user);
    }

    @Override
    public UserDto getOne(long id) {
        return mapper.userToUserDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Override
    public UserDto update(UserDto updateUser) {
        User user = userRepository.findById(updateUser.getId()).orElseThrow();
        User update = mapper.userDtoToUser(updateUser);
        user.setName(update.getName());
        user.setEmail(updateUser.getEmail());
        user.setSurname(update.getSurname());
        user.setPassword(update.getPassword());
        user = userRepository.save(user);
        return mapper.userToUserDto(user);
    }

}
