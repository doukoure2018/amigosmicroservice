package com.amigoscode.fraud.repository;

import com.amigoscode.fraud.entity.FraudCheckHistory;
import com.amigoscode.fraud.payload.FraudDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository  extends JpaRepository<FraudCheckHistory,Integer> {

    FraudCheckHistory findByCustomerId(Integer customerId);
}
