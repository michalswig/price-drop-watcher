package com.mike.pricedropwatcher.providers;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProviderFactory {

  private final Map<String, PricingProvider> providers;

  public ProviderFactory(Map<String, PricingProvider> providers) {
    this.providers = providers;
  }

  public PricingProvider getProvider(String sourceType) {
    PricingProvider provider = providers.get(sourceType);
    if (provider == null) {
      throw new IllegalArgumentException("No such provider: " + sourceType);
    }
    return provider;
  }
}
