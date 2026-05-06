package com.training.service;

import com.training.dto.ProjectDTO;
import com.training.dto.ProjectResponseDTO;
import com.training.entity.Project;
import com.training.entity.User;
import com.training.exception.ResourceNotFoundException;
import com.training.exception.UnAuthorizedException;
import com.training.repository.ProjectRepository;
import com.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProjectResponseDTO createProject(ProjectDTO dto)
    {
//        Step 1-Get logged In user email
        String email= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
//          Step 2-Fetch user from Database
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
//          Step 3- map dTO-eneties
        Project project=modelMapper.map(dto,Project.class);
//        step 4- set user
        project.setUser(user);
//          Step 5- save in db
        Project savedProject=projectRepository.save(project);

        return modelMapper.map(savedProject,ProjectResponseDTO.class);
    }

    public List<ProjectResponseDTO> getUserProjects()
    {
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("USER NOT FOUND"));

        List<Project> projects=projectRepository.findByUserId(user.getId());

        return projects.stream()
                .map(project -> modelMapper.map(project,ProjectResponseDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteProject(Long projectId)
    {
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        Project project=projectRepository.findById(projectId)
                .orElseThrow(()->new ResourceNotFoundException("Project Not Found"));

        if(!project.getUser().getEmail().equals(email))
        {
            throw new UnAuthorizedException("You don't have authority to delete this project");
        }
        projectRepository.delete(project);
    }
}
