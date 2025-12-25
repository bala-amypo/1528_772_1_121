package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionServiceImpl(ExamSessionRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamSession create(ExamSession session) {
        return repo.save(session);
    }

    @Override
    public List<ExamSession> list() {
        return repo.findAll();
    }

    @Override
    public ExamSession get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }
}
