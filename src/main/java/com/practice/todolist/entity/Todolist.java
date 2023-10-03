package com.practice.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Todolist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private int idx; // 번호

    @Column(name = "DATE", nullable = false)
    private LocalDate localDate; // 날짜

    @Column(name = "CONTENT", nullable = false)
    private String content; // 내용

    @Column(name = "CHECKED", nullable = false)
    private boolean checked; // 완료 여부 0: 미완료 1: 완료

    public Todolist() {
        // 파라미터 없는 기본 생성자
    }

    public Todolist(String content) {
        this.content = content;
        this.localDate = LocalDate.now();
        this.checked = false;
    }

    @Override
    public String toString() {
        return "Todolist{" +
                "idx=" + idx +
                ", localDate=" + localDate +
                ", content='" + content + '\'' +
                ", checked=" + checked +
                '}';
    }
}

