package com.backend.backend.service;

import com.backend.backend.domain.User;
import com.backend.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void buyCryptocurrency(String email, String cryptoName, Long value) {
        User user = userRepository.findByEmail(email);
        user.getCrytpos().forEach(crypto -> {
            if(crypto.getName().equals(cryptoName)){
                crypto.setValue(crypto.getValue() + value);
            }
        });
        user.setCreditCardBalance(user.getCreditCardBalance() - value);
    }

    @Override
    public void sellCryptocurrency(String email, String cryptoName, Long value) {
        User user = userRepository.findByEmail(email);
        user.getCrytpos().forEach(crypto -> {
            if(crypto.getName().equals(cryptoName)){
                crypto.setValue(crypto.getValue() - value);
            }
        });
        user.setCreditCardBalance(user.getCreditCardBalance() + value);
    }

    @Override
    public void addToBalance(String email, Long value) {
        User user = userRepository.findByEmail(email);
        user.setCreditCardBalance(user.getCreditCardBalance() + value);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
