package com.higortavares.appmetricas.controller;

import com.higortavares.appmetricas.metrics.MetricCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloWorldController {

  @Autowired private MetricCounterService metricCounterService;

  @GetMapping("/{success}")
  public ResponseEntity<?> sayHello(@PathVariable("success") boolean success){
    if(success) {
      metricCounterService.incrementEndpointStatus("say_hello", "200 OK");
      return ResponseEntity.ok("Hello World!");
    }
    else {
      metricCounterService.incrementEndpointStatus("say_hello","500 - INTERNAL SERVER ERROR");
      return ResponseEntity.internalServerError().build();
    }
  }

}
