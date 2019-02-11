package com.currency.conversion.rest;

import com.currency.conversion.client.CurrencyLayerClient;
import com.currency.conversion.model.CurrencyLayer;
import com.currency.conversion.service.CurrencyLayerService;
import com.currency.conversion.service.CurrencyLayerServiceCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyConversionControllerApi.class, CurrencyLayerService.class})
@WebMvcTest(CurrencyConversionControllerApi.class)
public class CurrencyConversionControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyLayerService currencyLayerService;

    @MockBean
    private CurrencyLayerClient currencyLayerClient;

    @MockBean
    private CurrencyLayerServiceCache currencyLayerServiceCache;

    @Test
    public void getCurrencyConversion_thenStatus200Ok() throws Exception {

        //given
        Map<String, BigDecimal> quotes = new HashMap<>();
        quotes.put("USDBRL", new BigDecimal("3.7398"));
        quotes.put("USDUSD", BigDecimal.ONE);

        CurrencyLayer currencyLayer = CurrencyLayer.builder().source("USD").timestamp("1549896845").quotes(quotes).build();

        //when
        when(currencyLayerServiceCache.currencyLayerFindAll()).thenReturn(currencyLayer);

        this.mockMvc.perform(get("/currencyConversion")
            .param("from", "BRL")
            .param("to", "USD")
            .param("amount", "123.34")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void getCurrencyConversion_thenStatus404Ok() throws Exception {


        //when
        when(currencyLayerServiceCache.currencyLayerFindAll()).thenReturn(null);

        this.mockMvc.perform(get("/currencyConversion")
            .param("from", "BRL")
            .param("to", "USD")
            .param("amount", "123.34")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

}
