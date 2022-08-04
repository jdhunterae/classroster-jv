package com.sg.classroster.ui;

import java.util.List;

import com.sg.classroster.dto.Student;

public class ClassRosterView {
    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create new Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public Student getNewStudentInfo() {
        Student currentStudent = new Student(io.readString("Please enter Student ID"));
        currentStudent.setFirstName(io.readString("Please enter First Name"));
        currentStudent.setLastName(io.readString("Please enter Last Name"));
        currentStudent.setCohort(io.readString("Please enter Cohort"));

        return currentStudent;
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.print("Student successfully created.");
        io.readString("Please press enter to continue.");
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student student : studentList) {
            String info = String.format("%s : %s %s %s",
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(), student.getCohort());
            io.print(info);
        }

        io.readString("Please press enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display Student ===");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID");
    }

    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(String.format("%s %s", student.getFirstName(), student.getLastName()));
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }

        io.readString("Please press enter to continue.");
    }

    public void displayRemoveStudentBanner() {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Student student) {
        if (student != null) {
            io.print("Student successfully removed.");
        } else {
            io.print("No such student.");
        }

        io.readString("Please press enter to continue.");
    }

    public void displayExitBanner() {
        io.print("=== Good Bye! ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("=== Unknown Command ===");
    }
}
