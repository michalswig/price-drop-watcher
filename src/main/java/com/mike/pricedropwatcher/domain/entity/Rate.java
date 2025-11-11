package com.mike.pricedropwatcher.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record Rate(
    String lowest,
    @JsonProperty("extracted_lowest") BigDecimal extractedLowest,
    @JsonProperty("before_taxes_fees") String beforeTaxesFees,
    @JsonProperty("extracted_before_taxes_fees") BigDecimal extractedBeforeTaxesFees) {}
