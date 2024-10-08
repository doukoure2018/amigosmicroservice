package com.amigoscode.fraud.service;

import com.amigoscode.fraud.payload.FraudDto;

public interface FraudService {

    void saveFraud(Integer customerId);

    Boolean isFraudulentCustomer(Integer customerId);
}
