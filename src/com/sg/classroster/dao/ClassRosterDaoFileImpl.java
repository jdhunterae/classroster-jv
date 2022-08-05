package com.sg.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.classroster.dto.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
    public static final String ROSTER_FILE = "res/roster.txt";
    public static final String DELIMITER = "::";

    final Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    private String marshalStudent(Student student) {
        String studentAsText = student.getStudentId() + DELIMITER;
        studentAsText += student.getFirstName() + DELIMITER;
        studentAsText += student.getLastName() + DELIMITER;
        studentAsText += student.getCohort() + DELIMITER;

        return studentAsText;
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

    private void writeRoster() throws ClassRosterDaoException {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(ROSTER_FILE));
            String studentAsText;
            List<Student> studentList = new ArrayList<>(students.values());

            for (Student student : studentList) {
                studentAsText = marshalStudent(student);
                writer.println(studentAsText);
                writer.flush();
            }
        } catch (IOException e) {
            throw new ClassRosterDaoException("-_- Could not write roster data into memory", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
