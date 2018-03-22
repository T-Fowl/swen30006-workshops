package com.tfowl.lms.requests;

import com.tfowl.lms.model.HandInMethod;
import com.tfowl.lms.model.User;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class AssignmentCreateRequest {
    private User creator;
    private String title;
    private Path instructionsLocation;
    private LocalDateTime dueDateTime;
    private int maxAttempts;
    private boolean allowLateSubmissions;
    private HandInMethod method;

    public User getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public Path getInstructionsLocation() {
        return instructionsLocation;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean isAllowLateSubmissions() {
        return allowLateSubmissions;
    }

    public HandInMethod getMethod() {
        return method;
    }

    public AssignmentCreateRequest(User creator, String title, Path instructionsLocation, LocalDateTime dueDateTime, int maxAttempts, boolean allowLateSubmissions, HandInMethod method) {

        this.creator = creator;
        this.title = title;
        this.instructionsLocation = instructionsLocation;
        this.dueDateTime = dueDateTime;
        this.maxAttempts = maxAttempts;
        this.allowLateSubmissions = allowLateSubmissions;
        this.method = method;
    }
}
