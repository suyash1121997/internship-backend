package com.internship.Internship.model;

import jakarta.persistence.Entity;
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
    private String sNo;
    private String studentEmail;
    private String mentorEmail;
    private String internshipId;
}
