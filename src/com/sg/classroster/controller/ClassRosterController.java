package com.sg.classroster.controller;

import java.util.List;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

public class ClassRosterController {
    private ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    io.print("EXIT");
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }

        io.print("GOODBYE");
        io.close();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() {
        view.displayDisplayAllBanner();
        List<Student> students = dao.getAllStudents();
        view.displayStudentList(students);
    }

    private void viewStudent() {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
}
