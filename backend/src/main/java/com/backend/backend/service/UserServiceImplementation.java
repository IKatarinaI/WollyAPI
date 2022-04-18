package com.backend.backend.service;

import com.backend.backend.domain.Cryptocurrency;
import com.backend.backend.domain.User;
import com.backend.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        userRepository.save(user);
        return user;
    }

    public List<Cryptocurrency> getCryptoList(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("");
        }
        return user.getCurrencyList();
    }

    public Double getUsersMoney(String email) {
        User user = userRepository.findByEmail(email);
        return user.getCurrentCardBalance();
    }

    public void addMoney(String email, Double value) {
        User user = userRepository.findByEmail(email);
        user.setCurrentCardBalance(user.getCurrentCardBalance() + value);
        userRepository.save(user);
    }

    public void buyCryptocurrency(String email, String cryptoName, Double value) {
        User user = userRepository.findByEmail(email);
        user.getCurrencyList().forEach(crypto -> {
            if(crypto.getName().equals(cryptoName)){
                crypto.setValue(crypto.getValue() + value);
            }
        });
        user.setCurrentCardBalance(user.getCurrentCardBalance() - value);
    }

    public void sellCryptocurrency(String email, String cryptoName, Double value) {
        User user = userRepository.findByEmail(email);
        user.getCurrencyList().forEach(crypto -> {
            if(crypto.getName().equals(cryptoName)){
                crypto.setValue(crypto.getValue() - value);
            }
        });
        user.setCurrentCardBalance(user.getCurrentCardBalance() + value);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            user = userRepository.findByUserName(email);
        }

        if(user == null){
            throw new UsernameNotFoundException("Email is not registered here.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
