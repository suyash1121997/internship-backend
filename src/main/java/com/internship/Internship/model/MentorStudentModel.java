package com.internship.Internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MentorStudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sNo;
    private String studentEmail;
    private String mentorEmail;
    private String internshipId;
}
