package com.amazecare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private LocalDate billingDate;

    private double consultationFee;

    private double medicineCharges;

    private double otherCharges;

    private double totalAmount;
}
