package com.currency.conversion.service;

import com.currency.conversion.client.CurrencyLayerClient;
import com.currency.conversion.model.ConversionRequest;
import com.currency.conversion.model.ConversionResponse;
import com.currency.conversion.model.CurrencyLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyLayerService {

    private final static String QUOTE_KEY = "USD";

    private final CurrencyLayerClient currencyLayerClient;

    @Value("${integration.access_key}")
    private String accessKey;

    public Optional<ConversionResponse> currencyLayer(ConversionRequest conversionRequest){
        Optional<BigDecimal> fromQuote =  currencyLayerFindAll()
            .map(fromValue -> fromValue.getQuotes().get(QUOTE_KEY.concat(conversionRequest.getFrom())));

        Optional<BigDecimal> toQuote =  currencyLayerFindAll()
            .map(fromValue -> fromValue.getQuotes().get(QUOTE_KEY.concat(conversionRequest.getTo())));

        return currencyConversion(createdResponse(conversionRequest, fromQuote, toQuote));
    }

    private Optional<CurrencyLayer> currencyLayerFindAll() {
        return currencyLayerClient.currencyLayerFindAll(accessKey);
    }

    private ConversionResponse createdResponse(ConversionRequest conversionRequest, Optional<BigDecimal> fromQuote, Optional<BigDecimal> toQuote) {
        return ConversionResponse
            .builder()
            .fromValue(fromQuote)
            .toValue(toQuote)
            .amount(Optional.of(conversionRequest.getAmount()))
            .build();
    }


    private Optional<ConversionResponse> currencyConversion(ConversionResponse conversionResponse){
        BigDecimal fromValue = conversionResponse.getFromValue().get();
        BigDecimal toValue = conversionResponse.getToValue().get();
        BigDecimal amount = conversionResponse.getAmount().get();
        BigDecimal rate = divide(fromValue,toValue);
        conversionResponse.setConvertedValue(Optional.of(divide(amount, rate)));
        return Optional.of(conversionResponse);
    }

    private BigDecimal divide(BigDecimal v1 , BigDecimal v2){
       return v1.divide(v2,9, RoundingMode.HALF_UP);
    }
}
