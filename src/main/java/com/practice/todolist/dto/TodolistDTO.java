package com.practice.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodolistDTO {
    private int idx;
    private LocalDate localDate;
    private String content;
    private boolean checked;
}
