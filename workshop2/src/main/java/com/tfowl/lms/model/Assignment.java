package com.tfowl.lms.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

public class Assignment {

    private UUID id;
    private User creator;
    private String title;
    private Path instructionsLocation;
    private LocalDateTime dueDateTime;
    private int maxAttempts;
    private boolean allowLateSubmissions;
    private HandInMethod method;

    public Assignment(UUID id, User creator, String title, Path instructionsLocation, LocalDateTime dueDateTime, int maxAttempts, boolean allowLateSubmissions, HandInMethod method) {

        this.id = id;
        this.creator = creator;
        this.title = title;
        this.instructionsLocation = instructionsLocation;
        this.dueDateTime = dueDateTime;
        this.maxAttempts = maxAttempts;
        this.allowLateSubmissions = allowLateSubmissions;
        this.method = method;
    }

    public UUID getId() {
        return id;
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

    public User getCreator() {
        return creator;
    }
}
