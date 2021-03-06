package com.example.demo.modelDto;

import com.example.demo.model.UserAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @JsonProperty("id")
    private Long id;
    @Size(min = 3,max = 25, message = "Username is mandatory")
    @JsonProperty("username")
    private String username;
    @Size(min = 3,max = 25, message = "Surname is mandatory")
    @JsonProperty("surname")
    private String surname;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;
    @Size(min = 8,max = 15, message = "Password is mandatory")
    @NotNull
    @JsonProperty("password")
    private String password;


    @JsonProperty("authority")
    private UserAuthority authority;

    @JsonProperty("token")
    private String token;

}
