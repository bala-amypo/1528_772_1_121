package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {

    SeatingPlan create(Long sessionId);

    List<SeatingPlan> list();

    SeatingPlan get(Long id);
}
