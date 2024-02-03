package com.internship.Internship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDetails {
    private String internshipId;
    private String internshipTitle;
    private List<String> studentEmails;
}
