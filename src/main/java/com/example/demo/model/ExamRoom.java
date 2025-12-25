package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer capacity;

    @Column(name = "room_rows")
    private Integer rows;

    @Column(name = "room_columns")
    private Integer columns;

    public ExamRoom() {}

    // getters
    public Long getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public Integer getCapacity() { return capacity; }
    public Integer getRows() { return rows; }
    public Integer getColumns() { return columns; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public void setRows(Integer rows) { this.rows = rows; }
    public void setColumns(Integer columns) { this.columns = columns; }

    public void ensureCapacityMatches() {
        this.capacity = rows * columns;
    }

    // ===== BUILDER =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ExamRoom r = new ExamRoom();

        public Builder id(Long id) { r.setId(id); return this; }
        public Builder roomNumber(String roomNumber) { r.setRoomNumber(roomNumber); return this; }
        public Builder rows(Integer rows) { r.setRows(rows); return this; }
        public Builder columns(Integer columns) { r.setColumns(columns); return this; }
        public Builder capacity(Integer capacity) { r.setCapacity(capacity); return this; }

        public ExamRoom build() { return r; }
    }
}
