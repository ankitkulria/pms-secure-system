package com.training.controller;

import com.training.dto.ProjectDTO;
import com.training.dto.ProjectResponseDTO;
import com.training.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectDTO dto)
    {
        ProjectResponseDTO responseDTO = projectService.createProject(dto);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProjects()
    {
        List<ProjectResponseDTO> projects=projectService.getUserProjects();
        return ResponseEntity.ok(projects);
    }
}
