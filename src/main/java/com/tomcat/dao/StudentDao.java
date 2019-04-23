package com.tomcat.dao;

import com.tomcat.entity.Student;

import java.util.List;

public interface StudentDao {
    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);

    Student getByIdStudent(Long id);

    List<Student> getAllStudents();
}
