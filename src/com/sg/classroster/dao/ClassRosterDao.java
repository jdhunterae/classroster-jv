package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.dto.Student;

public interface ClassRosterDao {
    Student addStudent(String studentId, Student student) throws ClassRosterDaoException;

    List<Student> getAllStudents() throws ClassRosterDaoException;

    Student getStudent(String studentId) throws ClassRosterDaoException;

    Student removeStudent(String studentId) throws ClassRosterDaoException;
}
