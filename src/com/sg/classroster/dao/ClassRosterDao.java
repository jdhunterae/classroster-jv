package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.dto.Student;

public interface ClassRosterDao {
    Student addStudent(String studentId, Student student);

    List<Student> getAllStudents();

    Student getStudent(String studentId);

    Student removeStudent(String studentId);
}
