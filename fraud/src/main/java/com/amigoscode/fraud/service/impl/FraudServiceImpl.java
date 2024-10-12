package com.amigoscode.fraud.service.impl;

import com.amigoscode.fraud.entity.FraudCheckHistory;
import com.amigoscode.fraud.payload.FraudDto;
import com.amigoscode.fraud.repository.FraudRepository;
import com.amigoscode.fraud.service.FraudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudServiceImpl implements FraudService {
    private final FraudRepository fraudRepository;

    @Override
    public void saveFraud(Integer customerId) {
        FraudCheckHistory fraudCheckHistory = new FraudCheckHistory();
        fraudCheckHistory.setCreateAt(LocalDateTime.now());
        fraudCheckHistory.setIsFraudster(false);
        fraudCheckHistory.setCustomerId(customerId);
        fraudRepository.save(fraudCheckHistory);
    }

    @Override
    public Boolean isFraudulentCustomer(Integer customerId) {
        FraudCheckHistory fraudCheckHistory = fraudRepository.findByCustomerId(customerId);
        log.info("Insertin");
        return fraudCheckHistory.getIsFraudster();
    }
}
