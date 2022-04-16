package com.backend.backend.repository;

import com.backend.backend.domain.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
    Cryptocurrency findByName(String name);
}
