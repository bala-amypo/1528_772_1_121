package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;
import com.example.demo.repository.StudentRepository;

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
        return repository.save(session);
    }

    @Override
    public List<ExamSession> list() {
        return repository.findAll();
    }

    @Override
    public ExamSession createSession(ExamSession s) {
        return create(s);
    }

    @Override
    public ExamSession getSession(Long id) {
        return get(id);
    }

    @Override
    public ExamSession get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }
}
