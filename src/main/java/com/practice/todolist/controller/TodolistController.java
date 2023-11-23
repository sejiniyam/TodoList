package com.practice.todolist.controller;

import com.practice.todolist.entity.Todolist;
import com.practice.todolist.service.TodolistService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todolist")
public class TodolistController {

    private final TodolistService todolistService;

    // 할 일 목록 조회
    @GetMapping("")
    public String main(Model model){

        LocalDate nowDate = LocalDate.now(); // 현재 날짜
        List<Todolist> todolist = todolistService.list(nowDate);

        ArrayList<Todolist> sameDate = new ArrayList<>();
        todolist.forEach(todo -> {
            LocalDate todolistDate = todo.getLocalDate(); // db에 저장되어있는 날짜
            if (todolistDate.equals(nowDate)) { // db 날짜 = 현재 날짜?
                sameDate.add(todo);
            }
        });
        model.addAttribute("todolist", sameDate);
        model.addAttribute("date", nowDate);
        return "main";
    }

    // 날짜 별 조회
    @GetMapping("/{date}")
    public String dateMove(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model){
        List<Todolist> todolist = todolistService.dateMove(date);
        List<Todolist> sameDate = new ArrayList<>();
        for (Todolist todo : todolist) {
            LocalDate todolistDate = todo.getLocalDate(); // db에 저장되어있는 날짜
            if (todolistDate.equals(date)) { // db 날짜 = 조회 날짜
                sameDate.add(todo);
            }
        }
        model.addAttribute("todolist", sameDate);
        model.addAttribute("date", date);
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

    // 할 일 완료 여부 (체크 시 완료 줄, 완료 상태로 변경)
    @PostMapping("/{idx}/{checked}")
    public String updateTodoStatus(@PathVariable int idx, @PathVariable Boolean checked) {
        todolistService.updateTodoStatus(idx, checked);
        return "redirect:/todolist";
    }
}
