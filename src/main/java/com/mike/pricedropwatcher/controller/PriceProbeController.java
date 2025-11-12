package com.mike.pricedropwatcher.controller;

import com.mike.pricedropwatcher.providers.ProviderFactory;
import com.mike.pricedropwatcher.providers.PricingProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PriceProbeController {

    private final PricingProvider provider;

    public PriceProbeController(ProviderFactory factory) {
        // bean name must match your @Component("SERP_API_HOTELS")
        this.provider = factory.getProvider("SERP_API_HOTELS");
    }

    @GetMapping("/probe/price")
    public BigDecimal probe(@RequestParam("param") String param) {
        return provider.getCurrentPrice(param);
    }
}

