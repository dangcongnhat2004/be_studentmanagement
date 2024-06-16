package com.example.be.Dto;

import com.example.be.entity.Attendance.StatusNow;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceRequest {
    private int studentId;
    private Date attendance_date;
    private String attendance_ask;
    private String title;
    private StatusNow statusnow; // Sử dụng enum thay vì String
}
