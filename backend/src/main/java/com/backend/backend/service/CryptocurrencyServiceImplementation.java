package com.backend.backend.service;

import com.backend.backend.domain.Cryptocurrency;
import com.backend.backend.repository.CryptocurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class CryptocurrencyServiceImplementation implements CryptocurrencyService {
    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    public Collection<Double> getStatistic(String cryptoName) {
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findByName(cryptoName);
        return cryptocurrency.getValuesHistory();
    }

    @Override
    public Cryptocurrency getByName(String cryptoName){
        return cryptocurrencyRepository.findByName(cryptoName);
    }
}