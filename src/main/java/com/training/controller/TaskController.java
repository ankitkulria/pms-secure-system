package com.training.controller;

import com.training.dto.TaskDTO;
import com.training.dto.TaskResponseDTO;
import com.training.dto.TaskStatusUpdateDTO;
import com.training.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskDTO dto)
    {
        TaskResponseDTO task= taskService.createTask(dto);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestBody TaskStatusUpdateDTO dto
            )
    {
        taskService.updateStatus(id,dto);
        return ResponseEntity.ok("Task Updated Successfully");
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(
            @PathVariable Long projectId)
    {
        return ResponseEntity.ok(
                taskService.getTasksByProject(projectId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok("Task deleted successfully");
    }
}
