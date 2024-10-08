package com.amigoscode.fraud.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FraudDto {

    private Integer id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createAt;
}
