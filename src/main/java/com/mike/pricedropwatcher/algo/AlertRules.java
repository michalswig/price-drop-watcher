package com.mike.pricedropwatcher.algo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class AlertRules {

  private static final MathContext MC = MathContext.DECIMAL64;

  private AlertRules() {}

  public static boolean isSignificantDrop(
      BigDecimal current, BigDecimal ema, BigDecimal minimumDropToTriggerAlert) {
    if (current == null || ema == null || minimumDropToTriggerAlert == null) return false;
    if (minimumDropToTriggerAlert.compareTo(BigDecimal.ZERO) < 0) return false;
    var diff = ema.subtract(current, MC).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    return diff.compareTo(minimumDropToTriggerAlert) >= 0 && current.compareTo(ema) < 0;
  }
}
