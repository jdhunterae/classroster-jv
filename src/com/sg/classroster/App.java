package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;

public class App {
    public static void main(String[] args) throws Exception {
        ClassRosterController controller = new ClassRosterController();
        controller.run();
    }
}
