package com.mike.pricedropwatcher.providers;

import java.math.BigDecimal;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component("MOCK")
public class MockPricingProvider implements PricingProvider {

  private final Random random = new Random();

  @Override
  public BigDecimal getCurrentPrice(String externalId) {
    double base = 100;
    double noise = (random.nextDouble() - 0.5) * base;
    return BigDecimal.valueOf(base + noise).setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
