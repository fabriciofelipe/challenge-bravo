package com.currency.conversion.rest;

import com.currency.conversion.model.CurrencyLayer;
import com.currency.conversion.service.CurrencyLayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CurrencyConversionApi {

    private final CurrencyLayerService currencyLayerService;

    @GetMapping(value = "/currencyConversion")
    public ResponseEntity<CurrencyLayer> currencyConversion() {
        return currencyLayerService.currencyConversion()
            .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

}
