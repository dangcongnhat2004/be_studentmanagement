package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter

@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "class_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "class_users_fk"))
    private Classes classes;

    private String userName;

    private String name;

    private String address;

    private String schoolname;

    @JsonIgnore
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
