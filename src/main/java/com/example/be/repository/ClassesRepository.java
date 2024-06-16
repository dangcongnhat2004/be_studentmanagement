package com.example.be.repository;

import com.example.be.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClassesRepository extends JpaRepository<Classes,Integer> {
}
