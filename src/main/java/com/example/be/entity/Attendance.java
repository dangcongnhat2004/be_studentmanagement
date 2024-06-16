package com.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_attendance_fk"))
    private Users student;

    @Column(name = "attendance_date")
    @Temporal(TemporalType.DATE)
    private Date attendance_date;

    @Column(name = "ask_for_leave")
    private String attendance_ask;

    @Column(name = "title")
    private String attendance_title;


    @Enumerated(EnumType.STRING)
    @Column(name = "statusnow")
    private StatusNow statusnow;

    public enum StatusNow {
        NORMAL,
        LEAVE,
        PRESENT
    }



}
