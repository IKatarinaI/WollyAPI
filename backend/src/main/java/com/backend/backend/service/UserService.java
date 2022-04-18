package com.backend.backend.service;

import com.backend.backend.domain.Cryptocurrency;
import com.backend.backend.domain.User;

import java.util.List;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password);
    List<Cryptocurrency> getCryptoList(String email);
    Double getUsersMoney(String email);
    void addMoney(String email, Double value);
    void buyCryptocurrency(String email, String cryptoName, Double value);
    void sellCryptocurrency(String email, String cryptoName, Double value);
}
