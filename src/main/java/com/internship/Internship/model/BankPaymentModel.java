package com.internship.Internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "bankPaymentDetails")
@Data
@Builder
public class BankPaymentModel {
    @Id
    private String cardNumber;
    private long balance;
}
