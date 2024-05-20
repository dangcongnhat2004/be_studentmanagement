package com.example.be.controller;


import com.example.be.Dto.UsersDto;
import com.example.be.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsersService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getUserDetail(@PathVariable int id) {
        UsersDto userDetailDTO = userService.getUserDetailById(id);
        if (userDetailDTO != null) {
            return new ResponseEntity<>(userDetailDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
