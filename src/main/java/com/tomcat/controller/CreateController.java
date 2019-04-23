package com.tomcat.controller;

import com.tomcat.dao.StudentDao;
import com.tomcat.dao.impl.StudentDaoImpl;
import com.tomcat.entity.Course;
import com.tomcat.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/create")
public class CreateController extends HttpServlet {
    private StudentDao repository = StudentDaoImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter("id");
        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        Course course = Course.valueOf(request.getParameter("course"));
        String speciality = request.getParameter("speciality");
        Student student = new Student()
                .setName(name)
                .setAge(age)
                .setCourse(course)
                .setSpeciality(speciality);
        if (Objects.equals(studentId, null))
            repository.insertStudent(student);
        else {
            Long id = Long.parseLong(studentId);
            student.setId(id);
            repository.updateStudent(student);
        }
        response.sendRedirect(request.getContextPath() + "/list");
    }
}
