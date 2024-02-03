package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.ICartController;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j
public class CartControllerImpl implements ICartController {
    @Autowired
    ICartService cartService;
    @Override
    public ResponseEntity<ResponseModel> manageCart(String userId, Internship internship) {
        log.info("Email is 0" +  userId);
        cartService.addToCart(userId, internship);
        System.out.println("Email in cart is " + userId);
        ResponseModel addedToCart = ResponseModel.builder().statusCode(201).message("Added to cart").build();
        return new ResponseEntity<>(addedToCart, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Internship>> getAllItemsFromCart(String userId) {
        return new ResponseEntity<>(cartService.getAllItemsFromCart(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteFromCart(String userId, String internshipId) throws InternshipException {
        log.info("Request received for remove from cart for user "+ userId + " internship id is "+ internshipId);
       String message = cartService.removeFromCart(userId, internshipId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
