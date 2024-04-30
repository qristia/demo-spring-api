package com.example.demo.modules.task.controller;

import com.example.demo.modules.task.dto.UpdateTaskDto;
import com.example.demo.modules.task.enums.EStatus;
import com.example.demo.modules.task.model.Task;
import com.example.demo.modules.task.repository.TaskRepository;
import com.example.demo.modules.task.repository.TaskSpecs;
import com.example.demo.utils.Utils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "tasks")
public class TaskController {

    @Resource
    private TaskRepository taskRepository;

    @GetMapping(value = "{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        
        return task;
    }

    @GetMapping
    public List<Task> getAllFiltered(
            @RequestParam(value = "createdDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdDate,
            @RequestParam(value = "dueDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
            @RequestParam(value = "status", required = false) EStatus status) {

        Specification<Task> specs = Specification.where(null);

        if (createdDate != null) {
            specs = specs.and(TaskSpecs.hasCreatedDate(createdDate));
        }
        if (dueDate != null) {
            specs = specs.and(TaskSpecs.hasDueDate(dueDate));
        }
        if (status != null) {
            specs = specs.and(TaskSpecs.hasStatus(status));
        }

        return this.taskRepository.findAll(specs);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@Valid @RequestBody Task task) {
        if (Utils.isBeforeDate(task.getDueDate(), LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Due date should not be before creation date");
        }
        Task _task = new Task(task.getTitle(), task.getDescription(), task.getDueDate());
        return this.taskRepository.save(_task);
    }

    @PatchMapping("{id}")
    public Task update(@PathVariable("id") Integer id, @RequestBody UpdateTaskDto updatedTask) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (updatedTask.getDueDate() != null) {
            if (Utils.isBeforeDate(updatedTask.getDueDate(), task.getCreatedDate())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Due date should not be before creation date");
            }
        }

        Utils.copyNonNullProperties(updatedTask, task, "id");
        return this.taskRepository.save(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));

        this.taskRepository.delete(task);
    }
}
