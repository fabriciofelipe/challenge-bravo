package com.currency.conversion.service;

import com.currency.conversion.client.CurrencyLayerClient;
import com.currency.conversion.model.ConversionRequest;
import com.currency.conversion.model.ConversionResponse;
import com.currency.conversion.model.CurrencyLayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyLayerServiceTest {

    @Autowired
    private CurrencyLayerService currencyLayerService;

    @MockBean
    private CurrencyLayerClient currencyLayerClient;


    @Test
    public void getCurrencyConversion() {

        //given
        Optional<ConversionRequest> conversionRequest = Optional.of(ConversionRequest
            .builder()
            .from("BRL")
            .to("USD")
            .amount(new BigDecimal("123.34"))
            .build());

        Optional<ConversionResponse> conversionResponse = Optional.of(ConversionResponse
            .builder()
            .fromQuote(new BigDecimal("3.7398"))
            .toQuote(BigDecimal.ONE)
            .amount(new BigDecimal("123.34"))
            .source("USD")
            .timestamp("1549896845")
            .convertedValue(new BigDecimal("32.980373282"))
            .build());

        Map<String, BigDecimal> quotes = new HashMap<>();
        quotes.put("USDBRL", new BigDecimal("3.7398"));
        quotes.put("USDUSD", BigDecimal.ONE);

        CurrencyLayer currencyLayer = CurrencyLayer.builder().source("USD").timestamp("1549896845").quotes(quotes).build();

        //when
        when(currencyLayerClient.currencyLayerFindAll(any())).thenReturn(currencyLayer);

        // then
        assertThat(currencyLayerService.currencyLayer(conversionRequest))
            .isEqualTo(conversionResponse);
    }


}
