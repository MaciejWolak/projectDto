package com.example.demo.service.impl;

import com.example.demo.modelDto.UserDto;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    UserService userService;

    public static final String USERNAME = "Maciej";
    public static final String SURNAME = "Wolak";
    public static final String EMAIL = "maciejwolak@gmail.com";
    public static final String PASSWORD = "12345678";
    public static final String AUTHORITY = '['+"USER"+']';



    @Test
    void registerUserTest(){
        UserDto user = new UserDto();
        user.setUsername(USERNAME);
        user.setSurname(SURNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Collection<? extends GrantedAuthority> userA = userService.register(user).getAuthorities();
        String authority = userA.toString();
        assertEquals(AUTHORITY,authority,"Nie zarejstrowano");

    }
    @Test
    void loginUserTest(){
        UserDto user = new UserDto();
        user.setUsername(USERNAME);
        user.setSurname(SURNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        UserDetails userDetails = userService.register(user);
        userService.login(USERNAME,PASSWORD);
        assertNotNull(userDetails.getAuthorities(),"Nie zalogowało");
    }


}
