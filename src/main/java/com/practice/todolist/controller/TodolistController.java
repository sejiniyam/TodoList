package com.practice.todolist.controller;

import com.practice.todolist.entity.Todolist;
import com.practice.todolist.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todolist")
public class TodolistController {

    private final TodolistService todolistService;

    @Autowired
    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    // 할 일 목록 조회
    @GetMapping("")
    public String main(Model model){
        List<Todolist> todolist = todolistService.list();
        model.addAttribute("todolist", todolist);
        return "main";
    }

    // 할 일 추가
    @PostMapping("")
    public String addList(@RequestParam("content") String content) {
        System.out.println("입력 내용 (컨트롤러 부분) - " + content);
        todolistService.addList(content);
        return "redirect:/todolist";
    }

    // 할 일 삭제
    @PostMapping("/{idx}")
    public String deleteList(@PathVariable int idx){
        System.out.println("삭제할 idx (컨트롤러 부분) - " + idx);
        todolistService.deleteList(idx);
        return "redirect:/todolist";
    }
}
