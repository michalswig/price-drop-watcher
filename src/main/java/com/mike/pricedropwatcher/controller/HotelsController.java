package com.mike.pricedropwatcher.controller;

import com.mike.pricedropwatcher.service.SerpApiService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelsController {
  private final SerpApiService serpApiService;

  @GetMapping("/search")
  public Map<String, Object> search(
      @RequestParam String city,
      @RequestParam String checkIn,
      @RequestParam String checkOut,
      @RequestParam(defaultValue = "2") int adults,
      @RequestParam(defaultValue = "PLN") String currency) {
    return serpApiService.searchHotel(city, checkIn, checkOut, adults, currency);
  }
}
