package com.mike.pricedropwatcher.algo;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlertRulesTest {

  @Test
  void returnTrueIfAlertShouldBeTriggered() {
    // given
    BigDecimal currentAmount = BigDecimal.valueOf(320);
    BigDecimal ema = BigDecimal.valueOf(380);
    BigDecimal threshold = BigDecimal.valueOf(15);
    boolean significantDropTrigger = AlertRules.isSignificantDrop(currentAmount, ema, threshold);
    // when then
    Assertions.assertTrue(significantDropTrigger);
  }

  @Test
  void returnFalseIfAlertShouldNotBeTriggered() {
    // given
    BigDecimal currentAmount = BigDecimal.valueOf(320);
    BigDecimal ema = BigDecimal.valueOf(320);
    BigDecimal threshold = BigDecimal.valueOf(15);
    boolean significantDropTrigger = AlertRules.isSignificantDrop(currentAmount, ema, threshold);
    // when then
    Assertions.assertFalse(significantDropTrigger);
  }
}
