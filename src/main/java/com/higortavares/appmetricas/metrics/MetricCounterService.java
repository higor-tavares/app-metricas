package com.higortavares.appmetricas.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricCounterService {
  @Autowired
  private MeterRegistry meterRegistry;

  public void incrementEndpointStatus(String endpoint, String status){
    var counter = Counter.builder(endpoint).tag("status_code",status).register(meterRegistry);
    counter.increment(1.0);
  }
}
