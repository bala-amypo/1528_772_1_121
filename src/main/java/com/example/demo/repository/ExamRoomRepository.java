package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExamRoom;

public interface ExamRoomRepository extends JpaRepository<ExamRoom , Long> {
    
}
