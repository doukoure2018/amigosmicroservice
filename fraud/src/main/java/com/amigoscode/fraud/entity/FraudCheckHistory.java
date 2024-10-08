package com.amigoscode.fraud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fraudCheckHistory")
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "fraud_id_sequence"
    )
    private Integer id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createAt;
}
