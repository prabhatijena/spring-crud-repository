package com.coolcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolcoder.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
