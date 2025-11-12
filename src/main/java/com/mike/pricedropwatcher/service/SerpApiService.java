package com.mike.pricedropwatcher.service;

import com.mike.pricedropwatcher.config.SerpApiProperties;
import com.mike.pricedropwatcher.domain.entity.HotelSearchResponse;
import com.mike.pricedropwatcher.domain.entity.Property;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class SerpApiService {
  private final RestClient serpRestClient;
  private final SerpApiProperties properties;

  public List<Property> searchHotel(
      String city, String checkIn, String checkOut, int numberOfGuests, String currency) {

    HotelSearchResponse body =
        serpRestClient
            .get()
            .uri(
                uri ->
                    uri.queryParam("engine", "google_hotels")
                        .queryParam("q", city)
                        .queryParam("check_in_date", checkIn)
                        .queryParam("check_out_date", checkOut)
                        .queryParam("adults", numberOfGuests)
                        .queryParam("currency", currency)
                        .queryParam("api_key", properties.apiKey())
                        .build())
            .retrieve()
            .onStatus(
                HttpStatusCode::is4xxClientError,
                (req, res) -> {
                  if (res.getStatusCode().value() == 429) {
                    throw new IllegalStateException(
                        "SerpAPI rate limit hit (429). Try again later.");
                  }
                  throw new IllegalArgumentException(
                      "SerpAPI request invalid: " + res.getStatusCode());
                })
            .onStatus(
                HttpStatusCode::is5xxServerError,
                (req, res) ->
                    new IllegalStateException("SerpAPI server error: " + res.getStatusCode()))
            .body(new ParameterizedTypeReference<>() {});

    assert body != null;
    return body.properties();
  }
}
