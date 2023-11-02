package com.internship.Internship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Internship {
    private String id;
    private String title;
    private String description;
    private String location;
    private String duration;
    private int price;
    private String requirements;
    private int seats;
    private String mentorEmail;
}
