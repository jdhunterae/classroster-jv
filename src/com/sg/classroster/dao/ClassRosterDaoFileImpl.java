package com.sg.classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sg.classroster.dto.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    final Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) {
        return students.put(studentId, student);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
        return students.remove(studentId);
    }

    private Student unmarshalStudent(String studentAsText) {
        // Expecting studentId :: firstName :: lastName :: cohort
        // example: 0001 :: Ada :: Lovelace :: Java-September-1842

        String[] tokens = studentAsText.split(DELIMITER);

        Student student = new Student(tokens[0]);
        student.setFirstName(tokens[1]);
        student.setLastName(tokens[2]);
        student.setCohort(tokens[3]);

        return student;
    }
}
