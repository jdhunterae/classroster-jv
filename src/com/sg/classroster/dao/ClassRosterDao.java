package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.dto.Student;

public interface ClassRosterDao {
    Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException;

    List<Student> getAllStudents() throws ClassRosterPersistenceException;

    Student getStudent(String studentId) throws ClassRosterPersistenceException;

    Student removeStudent(String studentId) throws ClassRosterPersistenceException;
}
