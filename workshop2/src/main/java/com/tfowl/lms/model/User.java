package com.tfowl.lms.model;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String firstName;
    private String familyName;
    private List<Enrollment> enrollments;

    public User(int id, String email, String firstName, String familyName, List<Enrollment> enrollments) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.familyName = familyName;
        this.enrollments = enrollments;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
