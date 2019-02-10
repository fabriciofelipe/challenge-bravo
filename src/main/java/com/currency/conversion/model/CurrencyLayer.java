package com.currency.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrencyLayer {


    private String source;
    private String timestamp;
    private Map<String, Float> quotes;
}
