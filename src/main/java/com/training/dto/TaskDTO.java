package com.training.dto;


import com.training.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskDTO {

    @NotBlank(message = "Name is required")
    private String title;
    private String description;
    @NotNull(message = "Status is required")
    private TaskStatus status;
    @NotNull(message = "Project ID is required")
    private Long projectId;
}
