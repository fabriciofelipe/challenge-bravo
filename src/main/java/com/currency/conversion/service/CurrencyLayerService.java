package com.currency.conversion.service;

import com.currency.conversion.client.CurrencyLayerClient;
import com.currency.conversion.model.CurrencyLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyLayerService {

    private final CurrencyLayerClient currencyLayerClient;

    @Value("${integration.access_key}")
    private String accessKey;

    public Optional<CurrencyLayer> currencyConversion(){
        return currencyLayerClient.currencyLayerFindAll(accessKey);
    }
}
