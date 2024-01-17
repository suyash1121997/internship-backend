package com.internship.Internship.service.impl;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.CartModel;
import com.internship.Internship.repository.ICartRepository;
import com.internship.Internship.service.ICartService;
import lombok.AllArgsConstructor;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
    public String removeFromCart(String userId, Internship internship) throws InternshipException {
       List<CartModel> cartModel =  cartRepository.findByUserId(userId);
       CartModel cartModel1 = cartModel.stream().filter(Objects::nonNull)
               .filter(e -> e.getInternship().getId().equals(internship.getId())).findAny().orElse(null);
       if(cartModel1!=null) {
           cartRepository.delete(cartModel1);
           return "Item deleted successfully from cart";
       }
       throw new InternshipException(404, "item does not exists in cart");
    }

    @Override
    public List<Internship> getAllItemsFromCart(String userId) {
        return cartRepository.findAllByUserId(userId).stream().map(CartModel::getInternship).collect(Collectors.toList());
    }
}
