package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom room;

    @Column(columnDefinition = "TEXT")
    private String arrangementJson;

    private LocalDateTime generatedAt;

    public SeatingPlan() {}

    @PrePersist
    public void onCreate() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public ExamSession getExamSession() { return examSession; }
    public ExamRoom getRoom() { return room; }
    public String getArrangementJson() { return arrangementJson; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setExamSession(ExamSession examSession) { this.examSession = examSession; }
    public void setRoom(ExamRoom room) { this.room = room; }
    public void setArrangementJson(String arrangementJson) { this.arrangementJson = arrangementJson; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final SeatingPlan p = new SeatingPlan();

        public Builder id(Long id) { p.setId(id); return this; }
        public Builder examSession(ExamSession examSession) { p.setExamSession(examSession); return this; }
        public Builder room(ExamRoom room) { p.setRoom(room); return this; }
        public Builder arrangementJson(String arrangementJson) { p.setArrangementJson(arrangementJson); return this; }
        public Builder generatedAt(LocalDateTime generatedAt) { p.setGeneratedAt(generatedAt); return this; }

        public SeatingPlan build() { return p; }
    }
}
