package com.currency.conversion.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConversionResponse {

    private BigDecimal fromQuote;
    private BigDecimal toQuote;
    private BigDecimal  amount;
    private BigDecimal  convertedValue;
    private String source;
    private String timestamp;
}
