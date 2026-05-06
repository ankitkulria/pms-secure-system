package com.training.dto;

import com.training.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO {

    private Long taskId;
    private String title;
    private String description;
    private TaskStatus status;
}