package com.tfowl.lms.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

public class Submission {
    private UUID id;
    private Assignment assignment;
    private User submittor;
    private LocalDateTime time;
    private Path submission;
    private String notes;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Path getSubmission() {
        return submission;
    }

    public String getNotes() {
        return notes;
    }

    public Submission(UUID id, Assignment assignment, User submittor, LocalDateTime time, Path submission, String notes) {

        this.id = id;
        this.assignment = assignment;
        this.submittor = submittor;
        this.time = time;
        this.submission = submission;
        this.notes = notes;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public User getSubmittor() {
        return submittor;
    }
}
