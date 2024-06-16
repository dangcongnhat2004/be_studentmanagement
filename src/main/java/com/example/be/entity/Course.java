package com.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameCourse;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "teacher_course_fk"))
    private Users teacher;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "classes_course_fk"))
    private Classes classes;

    @ManyToOne
    @JoinColumn(name = "Scholastic_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "scholastic_course_fk"))
    private Scholastic scholastic;
}
