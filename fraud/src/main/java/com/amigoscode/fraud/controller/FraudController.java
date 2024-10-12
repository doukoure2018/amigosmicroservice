package com.amigoscode.fraud.controller;



import com.amigoscode.fraud.payload.FraudDto;
import com.amigoscode.fraud.payload.HttpResponse;
import com.amigoscode.fraud.service.FraudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/fraud")
@RequiredArgsConstructor
@Slf4j
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Boolean> isFraudSter(@PathVariable(name = "customerId") Integer customerId) {
        fraudService.saveFraud(customerId);
        return ResponseEntity.ok(fraudService.isFraudulentCustomer(customerId));
    }
}
