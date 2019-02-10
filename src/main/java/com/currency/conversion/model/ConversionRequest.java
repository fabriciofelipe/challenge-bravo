package com.currency.conversion.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConversionRequest {

    private String from;
    private String to;
    private BigDecimal amount;
}
