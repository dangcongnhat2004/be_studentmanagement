package com.example.be.repository;

import com.example.be.entity.Scholastic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ScholasticRepository extends JpaRepository<Scholastic,Integer> {
}
