package com.internship.Internship.model;

import com.internship.Internship.model.compositekeys.StudentInternShipCompositeKey;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentInternship {
    @EmbeddedId
    private StudentInternShipCompositeKey studentInternShipCompositeKey;
    @Type(JsonBinaryType.class)
    @Column(name = "internships", columnDefinition = "jsonb")
    private InternshipModel internshipList;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date;
}