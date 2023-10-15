package com.internship.Internship.repository;

import com.internship.Internship.model.InternshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddInternshipRepository extends JpaRepository<InternshipModel, String> {
}
