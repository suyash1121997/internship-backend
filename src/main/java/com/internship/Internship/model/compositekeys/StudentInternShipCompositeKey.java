package com.internship.Internship.model.compositekeys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentInternShipCompositeKey {
    private String studentEmail;
    private String internshipId;
}
