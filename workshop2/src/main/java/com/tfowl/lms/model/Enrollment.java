package com.tfowl.lms.model;

import java.util.List;

public class Enrollment {
    private Subject subject;
    private List<Role> roles;

    public Subject getSubject() {
        return subject;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Enrollment(Subject subject, List<Role> roles) {

        this.subject = subject;
        this.roles = roles;
    }
}
