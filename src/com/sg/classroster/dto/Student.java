package com.sg.classroster.dto;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    final String studentId;
    private String cohort;

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.studentId);
        hash = 89 * hash + Objects.hashCode(this.cohort);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        if (object == this) {
            return true;
        }

        final Student other = (Student) object;
        if (!Objects.equals(other.studentId, studentId))
            return false;
        if (!Objects.equals(other.firstName, firstName))
            return false;
        if (!Objects.equals(other.lastName, lastName))
            return false;
        if (!Objects.equals(other.cohort, cohort))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Student{" + ", studentId=" + studentId +
                "firstName=" + firstName + ", lastName=" + lastName
                + ", cohort=" + cohort;
    }
}
