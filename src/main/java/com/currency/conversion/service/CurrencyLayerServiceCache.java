package com.currency.conversion.service;

import com.currency.conversion.client.CurrencyLayerClient;
import com.currency.conversion.model.CurrencyLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyLayerServiceCache {

    private final CurrencyLayerClient currencyLayerClient;


    @Value("${integration.access_key}")
    private String accessKey;

    @Cacheable("currencyLayer")
    public Optional<CurrencyLayer> currencyLayerFindAll() {
        log.info("findAll currency layer");
        return currencyLayerClient.currencyLayerFindAll(accessKey);
    }
}
