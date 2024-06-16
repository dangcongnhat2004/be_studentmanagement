package com.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Table(name = "scholastic")
public class Scholastic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nameYear;
        private String semester1;
        private String semester2;
        private String semesterAll;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private Users user;


}
