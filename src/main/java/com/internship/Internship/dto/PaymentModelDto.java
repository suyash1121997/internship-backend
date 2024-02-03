package com.internship.Internship.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModelDto{

    private String cardNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Expiry date must be in the future")
    private String expiryDate;
    private Integer cvv;
    private String name;
    private Integer amount;

    private List<String> internshipId;
    private String email;
}
