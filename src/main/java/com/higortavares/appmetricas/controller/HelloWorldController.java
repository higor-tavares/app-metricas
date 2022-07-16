package com.higortavares.appmetricas.controller;

import com.higortavares.appmetricas.metrics.MetricCounterService;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloWorldController {

  @Autowired private MetricCounterService metricCounterService;

  @GetMapping("/{success}")
  public ResponseEntity<?> sayHello(@PathVariable("success") boolean success) throws InterruptedException {
    var now = Calendar.getInstance().getTimeInMillis();
    Thread.sleep(Math.round(Math.random()*500));
    if(success) {
      metricCounterService.incrementEndpointStatus("say_hello", "200 OK");
      var diff = ((Calendar.getInstance().getTimeInMillis()-now) /1000.0);
      metricCounterService.responseTime(diff);
      return ResponseEntity.ok("Hello World!");
    }
    else {
      metricCounterService.incrementEndpointStatus("say_hello","500 - INTERNAL SERVER ERROR");
      var diff = ((Calendar.getInstance().getTimeInMillis()-now) /1000.0);
      metricCounterService.responseTime(diff);
      return ResponseEntity.internalServerError().build();
    }
  }
  @GetMapping("/usuariosLogados")
  public ResponseEntity<?> loggedUsers() throws InterruptedException {
    var value = Math.random()*100;
    var now = Calendar.getInstance().getTimeInMillis();
    Thread.sleep(Math.round(Math.random()*500));
    metricCounterService.percentage("usuarios_logados_sucess", new AtomicReference<Double>(value));
    var diff = ((Calendar.getInstance().getTimeInMillis()-now) /1000.0);
    metricCounterService.responseTime(diff);
    return ResponseEntity.ok(value);
  }
}
