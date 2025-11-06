package com.mike.pricedropwatcher.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mike.pricedropwatcher.domain.entity.PricePoint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmaTest {

  @Test
  void computeEmaWhenPassingValidPricePoints() {
    // given
    var ema =
        Ema.compute(
            List.of(
                new PricePoint(
                    1L, 1L, BigDecimal.valueOf(200), LocalDateTime.of(2025, 01, 01, 00, 00, 00)),
                new PricePoint(
                    1L, 2L, BigDecimal.valueOf(300), LocalDateTime.of(2025, 01, 02, 00, 00, 00)),
                new PricePoint(
                    1L, 3L, BigDecimal.valueOf(450), LocalDateTime.of(2025, 01, 03, 00, 00, 00)),
                new PricePoint(
                    1L, 4L, BigDecimal.valueOf(450), LocalDateTime.of(2025, 01, 04, 00, 00, 00)),
                new PricePoint(
                    1L, 5L, BigDecimal.valueOf(500), LocalDateTime.of(2025, 01, 05, 00, 00, 00))),
            new BigDecimal("0.1"));
    // when then
    Assertions.assertEquals(new BigDecimal("280.04"), ema);
  }

  @Test
  void ThrowExceptionWhenPassingNotValidAlpha() {
    // given
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Ema.compute(
                  List.of(
                      new PricePoint(
                          1L,
                          1L,
                          BigDecimal.valueOf(200),
                          LocalDateTime.of(2025, 01, 01, 00, 00, 00)),
                      new PricePoint(
                          1L,
                          2L,
                          BigDecimal.valueOf(300),
                          LocalDateTime.of(2025, 01, 02, 00, 00, 00)),
                      new PricePoint(
                          1L,
                          3L,
                          BigDecimal.valueOf(450),
                          LocalDateTime.of(2025, 01, 03, 00, 00, 00)),
                      new PricePoint(
                          1L,
                          4L,
                          BigDecimal.valueOf(450),
                          LocalDateTime.of(2025, 01, 04, 00, 00, 00)),
                      new PricePoint(
                          1L,
                          5L,
                          BigDecimal.valueOf(500),
                          LocalDateTime.of(2025, 01, 05, 00, 00, 00))),
                  new BigDecimal("1.1"));
            });
    // when then
    assertEquals("alpha must be in (0,1]", exception.getMessage());
  }
}
