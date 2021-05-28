package com.example.demo.service.impl;

import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthority;
import com.example.demo.modelDto.UserDto;
import com.example.demo.modelDto.UserGetDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.AuthResponse;
import com.example.demo.service.UserService;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Override
    public List<UserGetDto> getAll() {
        return mapper.usersGetAll(userRepository.findAll());
    }

    @Override
    public UserDto createNew(UserDto newUser) {
        User user= mapper.userDtoToUser(newUser);
        user = userRepository.save(user);
        return mapper.userToUserDto(user);
    }

    @Override
    public UserGetDto getOne(long id) {
        return mapper.userToUserGetDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Override
    public UserGetDto update(UserGetDto updateUser) {
        User user = userRepository.findById(updateUser.getId()).orElseThrow();
        User update = mapper.userGetDtoToUser(updateUser);
        user.setUsername(update.getUsername());
        user.setEmail(updateUser.getEmail());
        user.setSurname(update.getSurname());
        user.setPassword(update.getPassword());
        user = userRepository.save(user);
        return mapper.userToUserGetDto(user);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDetails;
    }

    public UserDetails findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public UserDetails register(UserDto userDto) {
        User newUser = mapper.userDtoToUser(userDto);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setAuthority(UserAuthority.USER);
        return userRepository.save(newUser);
    }

    public AuthResponse login(String username, String password) {
        User newUser = userRepository.findByUsername(username);
        if(newUser == null) {
            throw new UsernameNotFoundException(username);
        }

        if(!passwordEncoder.matches(password, newUser.getPassword())) {
            throw new UsernameNotFoundException(username);
        }

        newUser.setToken(UUID.randomUUID().toString());
        return new AuthResponse(newUser.getToken(),newUser.getAuthority());
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        user.setToken(null);
        userRepository.save(user);
    }

}
