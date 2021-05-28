package com.example.demo.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Basic
    @Column(unique = true)
    @Length(min=3,max = 25,message = "min 3 max 25 chars")
    @NotBlank(message = "Name is mandatory")
    private String username;

    @Basic
    @Column
    @Length(min=3,max = 25,message = "min 3 max 25 chars")
    private String surname;

    @Basic
    @Column
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Basic
    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(nullable = false)
    private UserAuthority authority;

    @Basic
    @Column
    private String token;


    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
