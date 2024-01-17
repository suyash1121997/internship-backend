package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.ICartController;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CartControllerImpl implements ICartController {
    @Autowired
    ICartService cartService;
    @Override
    public ResponseEntity<ResponseModel> manageCart(String userId, Internship internship) {
        cartService.addToCart(userId, internship);
        ResponseModel addedToCart = ResponseModel.builder().statusCode(201).message("Added to cart").build();
        return new ResponseEntity<>(addedToCart, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Internship>> getAllItemsFromCart(String userId) {
        return new ResponseEntity<>(cartService.getAllItemsFromCart(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteFromCart(String userId, Internship internship) throws InternshipException {
       String message = cartService.removeFromCart(userId, internship);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
