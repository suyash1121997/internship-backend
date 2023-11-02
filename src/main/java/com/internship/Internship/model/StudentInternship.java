package com.internship.Internship.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentInternship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sNo;
    private String studentEmail;
    @Type(JsonBinaryType.class)
    @Column(name = "internships", columnDefinition = "jsonb")
    private InternshipModel internshipList;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date;
    private String internshipId;
}
