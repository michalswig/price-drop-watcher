package com.mike.pricedropwatcher.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Property(
    String name,
    @JsonProperty("check_in_time") String checkInTime,
    @JsonProperty("check_out_time") String checkOutTime,
    @JsonProperty("rate_per_night") Rate ratePerNight,
    @JsonProperty("total_rate") Rate totalRate) {}
