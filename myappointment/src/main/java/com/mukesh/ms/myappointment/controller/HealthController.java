package com.mukesh.ms.myappointment.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/health")
  @Operation(description = "basic health check", tags = "health")
  public String healthCheck() {
    return "Application is running!!!";
  }
}
