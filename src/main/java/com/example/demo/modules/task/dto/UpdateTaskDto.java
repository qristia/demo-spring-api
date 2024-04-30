package com.example.demo.modules.task.dto;

import com.example.demo.modules.task.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {
    private String title;
    private String description;
    private LocalDate dueDate;
    private EStatus status;
}
