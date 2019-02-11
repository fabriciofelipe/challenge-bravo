package com.currency.conversion.service;

import com.currency.conversion.model.ConversionRequest;
import com.currency.conversion.model.ConversionResponse;
import com.currency.conversion.model.CurrencyLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyLayerService {

    private final static String QUOTE_KEY = "USD";
    private final CurrencyLayerServiceCache currencyLayerServiceCache;

    public Optional<ConversionResponse> currencyLayer(Optional<ConversionRequest> conversionRequest){
       return conversionRequest.flatMap(this::parse);
    }

    private Optional<ConversionResponse> parse(ConversionRequest conversionRequest) {
        Optional<CurrencyLayer> currencyLayer = Optional.ofNullable(currencyLayerServiceCache.currencyLayerFindAll());
        return currencyLayer.flatMap(cl-> currencyConversion(createdResponse(conversionRequest, cl)));
    }


    private ConversionResponse createdResponse(ConversionRequest conversionRequest, CurrencyLayer currencyLayer) {
        BigDecimal fromQuote = currencyLayer.getQuotes().get(QUOTE_KEY.concat(conversionRequest.getFrom()));
        BigDecimal toQuote = currencyLayer.getQuotes().get(QUOTE_KEY.concat(conversionRequest.getTo()));

        return ConversionResponse
            .builder()
            .source(currencyLayer.getSource())
            .timestamp(currencyLayer.getTimestamp())
            .fromQuote(fromQuote)
            .toQuote(toQuote)
            .amount(conversionRequest.getAmount())
            .build();
    }

    private Optional<ConversionResponse> currencyConversion(ConversionResponse conversionResponse){
        BigDecimal rate = divide(conversionResponse.getFromQuote(),conversionResponse.getToQuote());
        conversionResponse.setConvertedValue(divide(conversionResponse.getAmount(), rate));
        return Optional.of(conversionResponse);
    }

    private BigDecimal divide(BigDecimal v1 , BigDecimal v2){
       return v1.divide(v2,9, RoundingMode.HALF_UP);
    }
}
