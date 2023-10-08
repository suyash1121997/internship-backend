package com.internship.Internship.repository;

import com.internship.Internship.dto.RegistrationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISignUpRepository extends JpaRepository<RegistrationModel, String> {
}
