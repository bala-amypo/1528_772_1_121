package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repository;

    public ExamSessionServiceImpl(
            ExamSessionRepository repository,
            StudentRepository studentRepository
    ) {
        this.repository = repository;
    }

    @Override
    public ExamSession create(ExamSession session) {
        return createSession(session);
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate() == null) {
            throw new ApiException("Exam date is required");
        }

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("At least 1 student is required");
        }

        return repository.save(session);
    }

    @Override
    public List<ExamSession> list() {
        return repository.findAll();
    }

    @Override
    public ExamSession get(Long id) {
        return getSession(id);
    }

    @Override
    public ExamSession getSession(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }
}
