package com.internship.Internship.service;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.exception.InternshipException;

import java.util.List;

public interface ICartService {
    void addToCart(String userId, Internship internship);

    String removeFromCart(String userId, String internship) throws InternshipException;

    List<Internship> getAllItemsFromCart(String userId);
}
