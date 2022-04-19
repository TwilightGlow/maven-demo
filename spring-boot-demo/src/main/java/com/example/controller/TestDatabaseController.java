package com.example.controller;

import com.example.dao.TeacherMapper;
import com.example.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Javen
 * @date 2022/3/31
 */
@RestController
@RequiredArgsConstructor
public class TestDatabaseController {

    @Autowired
    private final TeacherMapper teacherMapper;

    @GetMapping("enquiry")
    public Integer enquiry(@RequestParam Integer page, @RequestParam Integer size) {
        int minRow = (page - 1) * size + 1;
        int maxRow = page * size;
        int index = (page - 1) * size;
        List<Teacher> teachers = teacherMapper.selectByPage(index, size);
        return teachers.size();
    }
}
