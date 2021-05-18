package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.modelDto.UserDto;
import com.example.demo.security.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto user) {
        UserDto newUser = userService.createNew(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        UserDto user = userService.getOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto user) {
        UserDto newUser = userService.getOne(id);
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setSurname(user.getSurname());
        userService.update(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public UserDetails register(@Valid @RequestBody UserDto user) {
        return userService.register(user);
    }

    @GetMapping("/details")
    public UserDetails details() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/logout")
    public boolean logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
        return true;
    }
}
