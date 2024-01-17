package com.internship.Internship.repository;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<CartModel, String> {
    List<CartModel> findByUserId(String userId);

    List<CartModel> findAllByUserId(String userId);
}
