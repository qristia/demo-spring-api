package com.example.demo.modules.task.repository;

import com.example.demo.modules.task.enums.EStatus;
import com.example.demo.modules.task.model.Task;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TaskSpecs {
    public static Specification<Task> hasCreatedDate(LocalDate createdDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdDate"), createdDate);
    }

    public static Specification<Task> hasDueDate(LocalDate dueDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dueDate"), dueDate);
    }

    public static Specification<Task> hasStatus(EStatus status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
