package com.example.demo.modules.task.repository;

import com.example.demo.modules.task.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll(Specification<Task> spec);
}
