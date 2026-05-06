package com.training.dto;


import com.training.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Long userId;

    private List<TaskResponseDTO> tasks;
}
