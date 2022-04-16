package com.backend.backend.service;

import com.backend.backend.domain.User;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    User getUser(String email);
    void buyCryptocurrency(String email, String cryptoName, Long value);
    void sellCryptocurrency(String email,String cryptoName, Long value);
    void addToBalance(String email,Long value);
}
