package com.mike.pricedropwatcher.providers;

import com.mike.pricedropwatcher.domain.entity.Property;
import com.mike.pricedropwatcher.service.SerpApiService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component("SERP_API_HOTELS")
@AllArgsConstructor
public class SerpApiHotelPricingProvider implements PricingProvider {
  private final SerpApiService service;

  @Override
  public BigDecimal getCurrentPrice(String externalId) {
    return searchFromParam(externalId).stream()
        .filter(p -> p.name() != null && p.name().equalsIgnoreCase("Hotel Belmonte"))
        .findFirst()
        .map(this::extractLowest)
        .orElseThrow(() -> new IllegalArgumentException("Hotel Belmonte not found in results"));
  }

  private BigDecimal extractLowest(Property p) {
    if (p.totalRate() != null) {
      if (p.totalRate().extractedLowest() != null) {
        return p.totalRate().extractedLowest();
      }
      if (p.totalRate().lowest() != null) {
        return parseMoney(p.totalRate().lowest());
      }
    }

    if (p.ratePerNight() != null) {
      if (p.ratePerNight().extractedLowest() != null) {
        return p.ratePerNight().extractedLowest();
      }
      if (p.ratePerNight().lowest() != null) {
        return parseMoney(p.ratePerNight().lowest());
      }
    }

    throw new IllegalStateException("No price found for hotel: " + p.name());
  }

  private BigDecimal parseMoney(String raw) {
    if (raw == null) return BigDecimal.ZERO;
    String num = raw.replaceAll("[^0-9,\\.]", "").replace(',', '.');
    return new BigDecimal(num);
  }

  private List<Property> searchFromParam(String parameter) {
    if (parameter == null || parameter.isBlank()) {
      throw new IllegalArgumentException("Parameter must not be empty");
    }

    String[] parts = parameter.split("\\|");
    if (parts.length != 5) {
      throw new IllegalArgumentException(
          "Invalid format. Expected: city|offSetDays|stayDays|numberOfGuests|currency");
    }

    String city = parts[0].trim();
    int offsetDays;
    int stayDays;
    int numberOfGuests;
    String currency = parts[4].trim();

    try {
      offsetDays = Integer.parseInt(parts[1].trim());
      stayDays = Integer.parseInt(parts[2].trim());
      numberOfGuests = Integer.parseInt(parts[3].trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "offSetDays, stayDays and numberOfGuests must be integers", e);
    }

    if (stayDays <= 0) {
      throw new IllegalArgumentException("stayDays must be > 0");
    }
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("numberOfGuests must be > 0");
    }

    LocalDate checkInDate = LocalDate.now().plusDays(offsetDays);
    LocalDate checkOutDate = checkInDate.plusDays(stayDays);

    String checkIn = checkInDate.toString(); // yyyy-MM-dd
    String checkOut = checkOutDate.toString(); // yyyy-MM-dd

    return service.searchHotel(city, checkIn, checkOut, numberOfGuests, currency);
  }
}
