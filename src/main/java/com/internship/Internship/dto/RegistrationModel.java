package com.internship.Internship.dto;

import com.internship.Internship.constants.LoginMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "signup")

public class RegistrationModel {

    @Id
    private String email;

    private String password;

    private String firstName;

    private String middleName;

    private String familyName;
    @Enumerated(EnumType.STRING)
    private LoginMode loginMode;

}
