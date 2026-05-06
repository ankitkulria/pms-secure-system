package com.training.dto;


import com.training.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {

    @NotNull
    private TaskStatus taskStatus;
}
