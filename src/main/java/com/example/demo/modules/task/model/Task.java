package com.example.demo.modules.task.model;

import com.example.demo.modules.task.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "missing field")
    @Column(name = "title")
    private String title;

    @NotNull(message = "missing field")
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(32) default 'PENDING'")
    private EStatus status = EStatus.PENDING;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "due_date")
    @NotNull(message = "missing field")
    private LocalDate dueDate;

    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
