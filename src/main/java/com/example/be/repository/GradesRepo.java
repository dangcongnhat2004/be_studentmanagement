package com.example.be.repository;

import com.example.be.entity.Grades;
import com.example.be.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface GradesRepo extends JpaRepository<Grades,Integer> {
    List<Grades> findByStudent(Users student);
}
