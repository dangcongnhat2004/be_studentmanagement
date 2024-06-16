package com.example.be.Dto;

import com.example.be.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String nameUser;
    private String email;
    private String address;
    private String numberPhone;
    private String schoolName;

    private String role;
    private String password;
    private List<Course> courses;
    private String nameClass;
    private Classes ourClasses;
    private Scholastic ourScholastic;
    private Users ourUsers;

    private BigDecimal frequentScore1;
    private BigDecimal frequentScore2;
    private BigDecimal frequentScore3;
    private BigDecimal frequentScore4;
    private BigDecimal frequentScore5;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private BigDecimal comments;
    private Grades ourGrade;
    private Users ourUser;

}
