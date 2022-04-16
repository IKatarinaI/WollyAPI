package com.backend.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Double creditCardBalance;
    @ManyToMany(fetch = EAGER)
    private Collection<Cryptocurrency> crytpos = new ArrayList<>();
    private PasswordEncoder passwordEncoder;

    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.email = email;
        this.password = this.passwordEncoder.encode(password);
    }
}
