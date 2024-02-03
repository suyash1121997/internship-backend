package com.internship.Internship.service.impl;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.CartModel;
import com.internship.Internship.repository.ICartRepository;
import com.internship.Internship.service.ICartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;
    @Override
    public void addToCart(String userId, Internship internship) {
        CartModel cartModel = CartModel.builder()
                .cartId(UUID.randomUUID().toString())
                .userId(userId)
                .internship(internship)
                .build();
        cartRepository.save(cartModel);
    }

    @Override
    public String removeFromCart(String userId, String internshipId) throws InternshipException {
       List<CartModel> cartModel =  cartRepository.findByUserId(userId);
       cartModel = cartModel.stream().filter(e -> e.getInternship().getId().equals(internshipId)).toList();
       log.info("Cart model to be deleted is " + cartModel.stream().toString());
        cartRepository.deleteAll(cartModel);
        return "Item deleted successfully from cart";
    }

    @Override
    public List<Internship> getAllItemsFromCart(String userId) {
        return cartRepository.findAllByUserId(userId).stream().map(CartModel::getInternship).collect(Collectors.toList());
    }
}
