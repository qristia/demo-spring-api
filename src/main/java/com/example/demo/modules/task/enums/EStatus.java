package com.example.demo.modules.task.enums;

import lombok.Getter;

@Getter
public enum EStatus {
    PENDING("PENDING"),
    PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED");

    private String value;

    EStatus(String value) {}
}
