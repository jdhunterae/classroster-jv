package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.service.ClassRosterServiceLayerImpl;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) throws Exception {
        UserIO io = new UserIOConsoleImpl();
        ClassRosterView view = new ClassRosterView(io);

        ClassRosterDao dao = new ClassRosterDaoFileImpl();
        ClassRosterServiceLayer service = new ClassRosterServiceLayerImpl(dao);

        ClassRosterController controller = new ClassRosterController(service, view);
        controller.run();
    }
}
