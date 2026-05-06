package com.training.service;

import com.training.dto.TaskDTO;
import com.training.dto.TaskResponseDTO;
import com.training.dto.TaskStatusUpdateDTO;
import com.training.entity.Project;
import com.training.entity.Task;
import com.training.exception.ResourceNotFoundException;
import com.training.exception.UnAuthorizedException;
import com.training.repository.ProjectRepository;
import com.training.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public TaskResponseDTO createTask(TaskDTO dto)
    {

        String email= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Project project=projectRepository.findById(dto.getProjectId())
                .orElseThrow(()->new ResourceNotFoundException("Project Not Found"));

        String role = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        if (!project.getUser().getEmail().equals(email) && !role.equals("ROLE_ADMIN")) {
            throw new RuntimeException("You are not allowed to access this project");
        }

        Task task=new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setProject(project);
            Task savedTask = taskRepository.save(task);
            return modelMapper.map(savedTask, TaskResponseDTO.class);

    }

    public void updateStatus(Long taskId, TaskStatusUpdateDTO dto)
    {
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        Task task=taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task Not Found"));

        if(!task.getProject().getUser().getEmail().equals(email))
        {
            throw new UnAuthorizedException("You are not allowed to access this task");
        }
        System.out.println(dto.getTaskStatus());
        task.setStatus(dto.getTaskStatus());
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {

        // 🔥 Step 1: get logged-in user
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // 🔥 Step 2: fetch task
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // 🔥 Step 3: ownership check
        if (!task.getProject().getUser().getEmail().equals(email)) {
            throw new RuntimeException("You are not allowed to delete this task");
        }

        // 🔥 Step 4: delete
        taskRepository.delete(task);
    }

    public List<TaskResponseDTO> getTasksByProject(Long projectId)
    {
        List<Task> tasks =
                taskRepository.findByProjectId(projectId);

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for(Task task : tasks)
        {
            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setTaskId(task.getTaskId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());

            responseList.add(dto);
        }

        return responseList;
    }
}
