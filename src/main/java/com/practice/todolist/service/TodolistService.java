package com.practice.todolist.service;

import com.practice.todolist.TodolistRepository;
import com.practice.todolist.entity.Todolist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class TodolistService {

    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    // 할 일 목록 조회
    public List<Todolist> list() {
        List<Todolist> todolist = todolistRepository.findAll();
        todolist.forEach(item -> System.out.println("불러올 내용 - " + item.toString()));
        return todolist;
    }

    // 할 일 추가
    @Transactional
    public void addList(String content) {
        Todolist todolist = new Todolist(content);
        todolistRepository.save(todolist);
    }

    // 할 일 삭제
    public void deleteList(int idx){
        todolistRepository.deleteById(idx);
    }

}
