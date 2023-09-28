package com.practice.todolist;

import com.practice.todolist.entity.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Integer> {

    @Override
    List<Todolist> findAll();

    @Override
    void deleteById(Integer idx);
}
