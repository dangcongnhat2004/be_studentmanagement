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
public class GradeDto {
    private int studentId;
    private int scholasticId;
    private int courseId;
    private BigDecimal frequentScore1;
    private BigDecimal frequentScore2;
    private BigDecimal frequentScore3;
    private BigDecimal frequentScore4;
    private BigDecimal frequentScore5;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private String comments;
    private String courseName;
    private  String nameUser;
    private  String nameYear;


}
