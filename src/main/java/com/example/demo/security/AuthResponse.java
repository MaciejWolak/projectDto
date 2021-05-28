package com.example.demo.security;

import com.example.demo.model.UserAuthority;
import lombok.Data;

@Data
public class AuthResponse {
    private String authToken;
    private UserAuthority authority;

    public AuthResponse(String authToken, UserAuthority authority) {
        this.authToken = authToken;
        this.authority = authority;
    }

}
