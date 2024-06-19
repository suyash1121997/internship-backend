package com.internship.Internship.model;

import com.internship.Internship.model.compositekeys.StudentInternShipCompositeKey;
import com.internship.Internship.model.compositekeys.StudentInternshipHistoryCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentInternshipHistory implements Serializable, Cloneable {
    @EmbeddedId
    private StudentInternshipHistoryCompositeKey studentInternshipHistoryCompositeKey;
    private LocalDateTime createdDate;


    @Override
    public StudentInternshipHistory clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (StudentInternshipHistory) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
