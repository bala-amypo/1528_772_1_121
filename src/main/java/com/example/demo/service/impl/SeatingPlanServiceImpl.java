package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository repo;

    public SeatingPlanServiceImpl(SeatingPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public SeatingPlan create(Long sessionId) {
        throw new ApiException("Not implemented");
    }

    @Override
    public List<SeatingPlan> list() {
        return repo.findAll();
    }

    @Override
    public SeatingPlan get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }
}
