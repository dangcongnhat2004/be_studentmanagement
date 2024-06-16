package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Table(name = "users")

public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    @JoinTable(
            name = "teacher_classes",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private Set<Classes> classes;
    @Column(name = "class_id")
    private Integer classId;

    private String nameUser;

    private String email;

    private String address;

    private String numberPhone;

    private String schoolName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Scholastic> scholastics;
    @JsonIgnore
    private String password;

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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
