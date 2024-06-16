package com.example.be.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseRequest {
    private String nameCourse;
    private Integer teacherId;
    private Integer scholasticId;
    private Integer classesId; // ID của lớp học

}
