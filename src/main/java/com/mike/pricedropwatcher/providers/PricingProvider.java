package com.mike.pricedropwatcher.providers;

import java.math.BigDecimal;

public interface PricingProvider {
  BigDecimal getCurrentPrice(String externalId);
}
