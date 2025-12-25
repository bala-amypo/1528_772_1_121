package com.example.demo.service;

import com.example.demo.model.ExamSession;
import java.util.List;

public interface ExamSessionService {
    ExamSession create(ExamSession session);
    List<ExamSession> list();
    ExamSession get(Long id);
}
