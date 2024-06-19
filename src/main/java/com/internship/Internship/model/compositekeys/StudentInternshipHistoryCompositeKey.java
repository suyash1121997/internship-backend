package com.internship.Internship.model.compositekeys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInternshipHistoryCompositeKey implements Serializable, Cloneable{
    private String studentEmail;
    private String internshipId;
    private String status;
}
