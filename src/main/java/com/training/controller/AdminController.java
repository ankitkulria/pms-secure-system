package com.training.controller;


import com.training.dto.ProjectResponseDTO;
import com.training.dto.UserResponseDTO;
import com.training.entity.Project;
import com.training.entity.User;
import com.training.repository.ProjectRepository;
import com.training.repository.TaskRepository;
import com.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @GetMapping("/data")
    public String adminData()
    {
        return "Only Admin can Access this";
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {

        List<Project> projects = projectRepository.findAll();

        List<ProjectResponseDTO> responseList = new ArrayList<>();

        for (Project project : projects) {
            ProjectResponseDTO dto = new ProjectResponseDTO();

            dto.setId(project.getId());
            dto.setName(project.getName());
            dto.setDescription(project.getDescription());
            dto.setUserId(project.getUser().getId());

            responseList.add(dto);
        }

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers()
    {
        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    dto.setEmail(user.getEmail());
                    dto.setRole(user.getRole());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(users);
    }

}
