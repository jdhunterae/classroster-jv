package com.sg.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {
    public static final String AUDIT_FILE = "res/audit.txt";

    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter writer;

        try {
            writer = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            LocalDateTime timestamp = LocalDateTime.now();
            writer.println(timestamp.toString() + ": " + entry);
            writer.close();
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not persist audit data.", e);
        }
    }
}
