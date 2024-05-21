package com.example.be.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "teacher_course_fk"))
    private Users teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_course_fk"))
    private Users student;
}
