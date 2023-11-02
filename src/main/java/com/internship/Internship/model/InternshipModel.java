package com.internship.Internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipModel {
    @Id
    private String internshipId;
    private String title;
    private String description;
    private String location;
    private String duration;
    private int price;
    private int seats;
    private String requirements;
    private String mentorEmail;
}
