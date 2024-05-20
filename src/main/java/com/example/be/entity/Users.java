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
    @JoinColumn(name = "class_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "class_users_fk"))
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public StatusNow getStatusNow() {
        return statusNow;
    }

    public void setStatusNow(StatusNow statusNow) {
        this.statusNow = statusNow;
    }
}
