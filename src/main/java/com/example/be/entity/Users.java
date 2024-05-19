package com.example.be.entity;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "class_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "class_fk"))
    private Classes classes;

    private String userName;

    private String name;

    private String address;

    private String schoolname;

    private String passwordhash;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private Role roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusnow")
    private StatusNow statusNow;



    public enum Role {
        ADMIN,
        TEACHER,
        STUDENT
    }

    public enum StatusNow {
        NORMAL,
        LEAVE,
        PRESENT
    }
}
