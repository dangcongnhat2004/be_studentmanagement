package com.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Data
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_grades_fk"))

    private Users student;

    @ManyToOne
    @JoinColumn(name = "scholatic_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "scholatic_grades_fk"))
    private Scholastic scholastic;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "course_grades_fk"))
    private Course course;

    @Column(name = "frequent_score1", precision = 5, scale = 2)
    private BigDecimal frequentScore1;

    @Column(name = "frequent_score2", precision = 5, scale = 2)
    private BigDecimal frequentScore2;

    @Column(name = "frequent_score3", precision = 5, scale = 2)
    private BigDecimal frequentScore3;

    @Column(name = "frequent_score4", precision = 5, scale = 2)
    private BigDecimal frequentScore4;

    @Column(name = "frequent_score5", precision = 5, scale = 2)
    private BigDecimal frequentScore5;

    @Column(name = "midterm_score", precision = 5, scale = 2)
    private BigDecimal midtermScore;

    @Column(name = "final_score", precision = 5, scale = 2)
    private BigDecimal finalScore;

    @Column(name = "average_score", precision = 5, scale = 2)
    private BigDecimal averageScore;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;



}
