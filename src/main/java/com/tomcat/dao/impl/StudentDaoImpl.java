package com.tomcat.dao.impl;

import com.tomcat.dao.StudentDao;
import com.tomcat.entity.Student;
import com.tomcat.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

@Log4j
public class StudentDaoImpl implements StudentDao {
    private static volatile StudentDaoImpl instance = null;
    private final static String SELECT_ALL = "SELECT a FROM Student a";

    @Override
    public void insert(Student student) {
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
    public void update(Student student) {
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
    public void delete(Long id) {
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
    public Student getById(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Student.class, id);
        } catch (Exception e) {
            log.error("Get by id error: " + e.getMessage());
            return new Student();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery(SELECT_ALL, Student.class).getResultList();
        } catch (Exception e) {
            log.error("Get all error: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public static StudentDao getInstance() {
        if (instance == null) {
            synchronized (StudentDaoImpl.class) {
                if (instance == null) {
                    instance = new StudentDaoImpl();
                }
            }
        }
        return instance;
    }
}
