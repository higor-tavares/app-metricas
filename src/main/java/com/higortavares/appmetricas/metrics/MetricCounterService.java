package com.higortavares.appmetricas.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.atomic.AtomicReference;
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

  public void percentage(String name, AtomicReference<Double> value) {
    try {
      var m = meterRegistry.get(name);
      if (m != null) {
        var g = m.gauge();
        meterRegistry.remove(g);
      }
    } catch (Exception e){
      //doNothing
    }
    Gauge gauge = Gauge.builder(name, value, AtomicReference::get).register(meterRegistry);
    System.out.println(gauge.value());
  }

}
