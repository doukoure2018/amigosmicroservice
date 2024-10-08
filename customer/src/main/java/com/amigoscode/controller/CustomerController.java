package com.amigoscode.controller;


import com.amigoscode.payload.CustomerDto;
import com.amigoscode.service.CustomerService;
import com.amigoscode.payload.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("/save")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody CustomerDto customerDto) throws Exception {
        log.info("new Customer registration {}",customerDto.getId());
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("customer", customerService.create(customerDto)))
                        .message(String.format("User account created for user %s", customerDto.getFirstName()))
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("")
    public ResponseEntity<HttpResponse> getAllCustomers() {

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("customers", customerService.getAll()))
                        .message(String.format("Customers retreived"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<HttpResponse> getCustomer(@PathVariable(name = "customerId") Integer customerId) {

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("customer", customerService.getById(customerId)))
                        .message(String.format("Customers retreived with id"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

}
