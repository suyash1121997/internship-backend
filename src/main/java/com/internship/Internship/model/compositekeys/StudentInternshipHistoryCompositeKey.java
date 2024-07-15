package com.internship.Internship.model.compositekeys;

import com.internship.Internship.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInternshipHistoryCompositeKey implements Serializable{
    private String studentEmail;
    private String internshipId;
    @Enumerated(EnumType.STRING)
    private Status status;
}
