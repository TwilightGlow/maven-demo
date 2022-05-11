package com.example.repository;

import com.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jinwei Zhang
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
