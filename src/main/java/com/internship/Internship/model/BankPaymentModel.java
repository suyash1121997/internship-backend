package com.internship.Internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bankPaymentDetails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankPaymentModel {
    @Id
    private String cardNumber;
    private long balance;
}
