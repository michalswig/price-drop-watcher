package com.mike.pricedropwatcher.algo;

import com.mike.pricedropwatcher.domain.entity.PricePoint;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public final class Ema {
  private static final MathContext MATH_CONTEXT = MathContext.DECIMAL64;
  private static final Integer SCALE = 2;

  private Ema() {}

  public static BigDecimal compute(List<PricePoint> pricePoints, BigDecimal alpha) {
    if (pricePoints == null || pricePoints.isEmpty()) return BigDecimal.ZERO;
    if (alpha == null
        || alpha.compareTo(BigDecimal.ZERO) <= 0
        || alpha.compareTo(BigDecimal.ONE) > 0)
      throw new IllegalArgumentException("alpha must be in (0,1]");

    var sorted =
        pricePoints.stream().sorted(Comparator.comparing(PricePoint::getTimestamp)).toList();

    BigDecimal ema = getBigDecimal(alpha, sorted);
    return ema.setScale(SCALE, RoundingMode.HALF_UP);
  }

  private static BigDecimal getBigDecimal(BigDecimal alpha, List<PricePoint> sorted) {
    BigDecimal ema = sorted.getFirst().getPrice();
    BigDecimal oneMinusAlpha = BigDecimal.ONE.subtract(alpha, MATH_CONTEXT);

    for (int i = 1; i < sorted.size(); i++) {
      BigDecimal price = sorted.get(i).getPrice();
      ema =
          alpha
              .multiply(price, MATH_CONTEXT)
              .add(oneMinusAlpha.multiply(ema, MATH_CONTEXT), MATH_CONTEXT);
    }
    return ema;
  }
}
