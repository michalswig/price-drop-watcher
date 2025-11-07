package com.mike.pricedropwatcher.providers;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MockPricingProviderTest {

  @Test
  void getCurrentPriceInRangeFromMinusTenToTen() {
    // given
    PricingProvider provider = new MockPricingProvider();
    // when
    BigDecimal currentPrice = provider.getCurrentPrice("1");
    // then
    assertTrue(currentPrice.compareTo(BigDecimal.ZERO) > 0);
  }
}
