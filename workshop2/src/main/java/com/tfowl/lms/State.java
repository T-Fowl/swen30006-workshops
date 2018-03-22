package com.tfowl.lms;

import com.tfowl.lms.model.Subject;
import com.tfowl.lms.model.User;

import java.util.Optional;

public class State {

    private User currentUser;
    private Subject currentSubject;
    private LearningManagementSystem lms;

    public State(LearningManagementSystem lms) {
        this.lms = lms;
    }

    public LearningManagementSystem getLms() {
        return lms;
    }

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Optional<Subject> getCurrentSubject() {
        return Optional.ofNullable(currentSubject);
    }

    public void setCurrentSubject(Subject currentSubject) {
        this.currentSubject = currentSubject;
    }
}
