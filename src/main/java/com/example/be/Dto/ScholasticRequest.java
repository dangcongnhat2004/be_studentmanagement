package com.example.be.Dto;

import com.example.be.entity.Classes;
import com.example.be.entity.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScholasticRequest {
    private String nameYear;
    private String semester1;
    private String semester2;
    private String semesterAll;
    private Integer userId;
    private Course ourCourse;
}
