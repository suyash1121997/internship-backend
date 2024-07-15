package com.internship.Internship.controller;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.StudentInternshipHistoryDto;
import com.internship.Internship.dto.UpdateInternship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.StudentInternshipHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "*")
public interface IStudentController {
    @PostMapping("/addToStudent")
    ResponseEntity<ResponseModel> addInternshipInAccount(@RequestParam String email, @RequestParam List<String> id) throws InternshipException;

    @PostMapping("/cancelInternship")
    ResponseEntity<ResponseModel> cancelInternship(@RequestParam String email, @RequestParam String id) throws InternshipException;
    @PutMapping("/updateStatus")
    ResponseEntity<ResponseModel> updateStatus(@RequestBody UpdateInternship updateInternship);
    @GetMapping("/getInternshipHistory")
    ResponseEntity<ResponseModel<List<StudentInternshipHistoryDto>>> getInternshipHistory(@RequestParam String email);
}
