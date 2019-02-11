package com.currency.conversion.client;

import com.currency.conversion.model.CurrencyLayer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="clapi", url = "${integration.clapi.base_url}")
public interface CurrencyLayerClient {

    @GetMapping(value = "/api/live")
    Optional<CurrencyLayer> currencyLayerFindAll(@RequestParam("access_key") String accessKey);
}
