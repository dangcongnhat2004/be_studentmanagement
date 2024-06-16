package com.example.be.controller;

import com.example.be.Dto.ReqRes;
import com.example.be.entity.Users;
import com.example.be.repository.GradesRepo;
import com.example.be.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class GradesController {
    @Autowired
    private OurUserRepo ourUserRepo;
    @GetMapping("/student/grade")
    public ResponseEntity<Object> getAllGrades(){
        return ResponseEntity.ok(ourUserRepo.findAll());
    }

}
