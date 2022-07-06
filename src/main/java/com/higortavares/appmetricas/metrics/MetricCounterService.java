package com.higortavares.appmetricas.metrics;

import com.higortavares.appmetricas.Value;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import java.security.Guard;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricCounterService {
  @Autowired
  private MeterRegistry meterRegistry;

  public void incrementEndpointStatus(String endpoint, String status){
    var counter = meterRegistry.counter(endpoint, "httpStatusResponse", status);
    counter.increment(1.0);
  }

  public void percentage(String name, Double value) {
    final var atomicReference = new AtomicReference<Double>();
    atomicReference.set(value);
    Gauge.builder(name, atomicReference, AtomicReference::get).strongReference(true)
        .register(meterRegistry);
  }

}
