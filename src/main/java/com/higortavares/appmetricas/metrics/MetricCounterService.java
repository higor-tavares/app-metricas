package com.higortavares.appmetricas.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricCounterService {
  private static final String RESPONSE_TIME = "tempo_resposta_http";
  private static final String RESPONSE_TIME_HELPER = "Tempo de resposta a uma requisição";
  private static final double[] BUCKETS = {0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5, 0.55, 0.6};
  @Autowired
  private MeterRegistry meterRegistry;
  @Autowired
  private CollectorRegistry collectorRegistry;

  private Histogram histogram;

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
    } catch (Exception e){}
    Gauge gauge = Gauge.builder(name, value, AtomicReference::get).register(meterRegistry);
    System.out.println(gauge.value());
  }

  public void responseTime(Double value) {
    if(this.histogram == null){
      this.histogram = Histogram.build(RESPONSE_TIME, RESPONSE_TIME_HELPER).buckets(BUCKETS).register(collectorRegistry);
    }
    this.histogram.observe(value);
  }
}
