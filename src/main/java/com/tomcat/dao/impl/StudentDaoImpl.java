package com.tomcat.dao.impl;

import com.tomcat.dao.StudentDao;
import com.tomcat.entity.Student;
import com.tomcat.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class StudentDaoImpl implements StudentDao {
    private static volatile StudentDaoImpl instance = null;

    @Override
    public void insertStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Insert error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void updateStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteStudent(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Delete error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Student getByIdStudent(Long id) {
        Session session = null;
        Student student = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            student = session.get(Student.class, id);
        } catch (Exception e) {
            log.error("Get by id error: " + e.getMessage());
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = null;
        List students = new ArrayList<Student>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            students = session.createQuery("SELECT a FROM Student a", Student.class).getResultList();
        } catch (Exception e) {
            log.error("Get all error: " + e.getMessage());
        } finally {
            session.close();
        }
        return students;
    }

    public static synchronized StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDaoImpl();
        }
        return instance;
    }
}
