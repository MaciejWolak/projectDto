package com.example.demo.modelDto;

import com.example.demo.model.UserAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
@Data
public class UserDto {
    @JsonProperty("id")
    private Long id;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("authority")
    private UserAuthority authority;

    @JsonProperty("token")
    private String token;
}
