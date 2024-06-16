package com.example.be.repository;

import com.example.be.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
}
