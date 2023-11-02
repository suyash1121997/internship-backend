package com.internship.Internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Builder
@Table(name = "paymentDetails")
public class PaymentMethod {
    @Id
    private String cardNumber;
    private Date date;
    private int cvv;
    private String name;
}
