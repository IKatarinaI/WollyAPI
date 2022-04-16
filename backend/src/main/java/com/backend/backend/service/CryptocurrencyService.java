package com.backend.backend.service;

import com.backend.backend.domain.Cryptocurrency;

import java.util.Collection;

public interface CryptocurrencyService {
    Collection<Double> getStatistic(String cryptoName);
    Cryptocurrency getByName(String cryptoName);
}
