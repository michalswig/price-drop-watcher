package com.mike.pricedropwatcher.providers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mike.pricedropwatcher.domain.entity.Property;
import com.mike.pricedropwatcher.domain.entity.Rate;
import com.mike.pricedropwatcher.service.SerpApiService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

class SerpApiHotelPricingProviderTest {

  @Mock SerpApiService service;

  @InjectMocks SerpApiHotelPricingProvider provider;

  @Test
  @ExtendWith(MockitoExtension.class)
  void getRateIfPassingValidExternalId() {
    // given
    String externalId = "Krynica-Zdrój|7|2|2|PLN";
    Rate rate = mock(Rate.class);
    when(rate.extractedLowest()).thenReturn(new BigDecimal(950));
    Property property = mock(Property.class);
    when(property.name()).thenReturn("Hotel Belmonte");
    when(property.ratePerNight()).thenReturn(rate);
    when(service.searchHotel(anyString(), anyString(), anyString(), anyInt(), anyString()))
        .thenReturn(List.of(property));
    // when
    BigDecimal currentPrice = provider.getCurrentPrice(externalId);
    // then
    Assertions.assertEquals(new BigDecimal(950), currentPrice);
  }
}
