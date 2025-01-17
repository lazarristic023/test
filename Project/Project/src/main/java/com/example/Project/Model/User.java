package com.example.Project.Model;



import com.example.Project.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotNull
    @Size(min=6, max=20)
    private String password;

    private Role role;

    private Boolean emailChecked;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;

    @NotEmpty
    private String phone;

    @NotNull
    private boolean blocked;

    public User(){}

    public User(String username, String email, String password, Role role,String city,String country,String phone, boolean blocked) {
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }


    public User(String city,String country,String phone){
        this.country = country;
        this.city = city;
        this.phone = phone;
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.emailChecked = user.getEmailChecked();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phone = user.getPhone();
        this.blocked = user.isBlocked();
    }

    @Override
    public String getUsername() {
        return username;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !blocked;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority(role.name()));
        return  role.getAuthorities();
    }

}