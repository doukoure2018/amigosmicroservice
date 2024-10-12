package com.amigoscode.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// This FRAUD is defined on Eureka server
@FeignClient("FRAUD")
public interface FraudClient {
    @GetMapping(path = "/api/v1/fraud/{customerId}")
     Boolean isFraudulentCustomer(@PathVariable(name = "customerId") Integer customerId);
}
