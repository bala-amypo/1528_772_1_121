package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repository;

    public ExamSessionServiceImpl(ExamSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        return repository.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }

    @Override
    public List<ExamSession> listSessions() {
        return repository.findAll();
    }
}
