package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService ctrl;

    public ExamRoomController(ExamRoomService ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom r) {
        return ResponseEntity.ok(ctrl.addRoom(r));
    }

    @GetMapping
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(ctrl.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamRoom> get(@PathVariable Long id) {
        return ResponseEntity.ok(ctrl.getRoom(id));
    }
}
