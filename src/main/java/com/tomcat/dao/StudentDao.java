package com.tomcat.dao;

import com.tomcat.entity.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student student);

    void update(Student student);

    void delete(Long id);

    Student getById(Long id);

    List<Student> getAll();
}
