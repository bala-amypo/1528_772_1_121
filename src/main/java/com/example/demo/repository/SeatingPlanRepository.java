package com.example.demo.repository;

import com.example.demo.model.SeatingPlan;
import com.example.demo.model.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {

    List<SeatingPlan> findByExamSessionId(Long sessionId);

    List<ExamSession> findByExamId(Long examId);
}
