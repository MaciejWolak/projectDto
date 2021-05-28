package com.example.demo.modelDto;

import com.example.demo.model.UserAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserGetDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;
    @Size(min = 3,max = 25, message = "Username is mandatory")
    @JsonProperty("username")
    private String username;
    @Size(min = 3,max = 25, message = "Surname is mandatory")
    @JsonProperty("surname")
    private String surname;

    @JsonProperty("authority")
    private UserAuthority authority;

}
