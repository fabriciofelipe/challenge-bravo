package com.currency.conversion.rest;

import com.currency.conversion.model.ConversionRequest;
import com.currency.conversion.model.ConversionResponse;
import com.currency.conversion.service.CurrencyLayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CurrencyConversionControllerApi {

    private final CurrencyLayerService currencyLayerService;

    @GetMapping(value = "/currencyConversion")
    public ResponseEntity<ConversionResponse> currencyConversion(
        @RequestParam("from") String from,
        @RequestParam("to") String to,
        @RequestParam("amount") BigDecimal amount) {

        Optional<ConversionRequest> conversionRequest = Optional.of(ConversionRequest
            .builder()
            .from(from)
            .to(to)
            .amount(amount)
            .build());

        return currencyLayerService.currencyLayer(conversionRequest)
            .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

}
