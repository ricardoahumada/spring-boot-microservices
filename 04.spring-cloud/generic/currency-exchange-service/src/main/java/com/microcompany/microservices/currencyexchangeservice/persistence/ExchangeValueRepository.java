package com.microcompany.microservices.currencyexchangeservice.persistence;

import com.microcompany.microservices.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    public ExchangeValue findByFromAndTo(String from, String to);
    ExchangeValue findByFrom (String from);
}
