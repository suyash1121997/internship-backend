package com.internship.Internship.controller;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.CartModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
public interface ICartController {

    @PostMapping("/addToCart")
    ResponseEntity<ResponseModel> manageCart(@RequestParam String userId, @RequestBody Internship internship);
    @GetMapping("/getAllItems")
    ResponseEntity<List<Internship>> getAllItemsFromCart(@RequestParam String userId);
    @DeleteMapping("/deleteFromCart")
    ResponseEntity<String> deleteFromCart(@RequestParam String userId,@RequestParam String internshipId) throws InternshipException;
}
