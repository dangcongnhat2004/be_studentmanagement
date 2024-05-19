package com.example.be.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "teacher_fk"))
    private Users teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_fk"))
    private Users student;

    @Column(name = "title")
    private String title;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "sent_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp sentAt;

    @Column(name = "read_status", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean readStatus;
}
