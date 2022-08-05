package com.sg.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(ROSTER_FILE)));

            String line;
            Student student;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                student = unmarshalStudent(line);
                students.put(student.getStudentId(), student);
            }
        } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException("-_- Could not load roster data into memory", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
