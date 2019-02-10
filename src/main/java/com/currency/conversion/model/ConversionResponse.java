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

    private Optional<BigDecimal> fromValue;
    private Optional<BigDecimal>  toValue;
    private Optional<BigDecimal>  amount;
    private Optional<BigDecimal>  convertedValue;
}
