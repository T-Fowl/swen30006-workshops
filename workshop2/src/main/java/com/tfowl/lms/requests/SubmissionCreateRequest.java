package com.tfowl.lms.requests;

import com.tfowl.lms.model.Assignment;
import com.tfowl.lms.model.User;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class SubmissionCreateRequest {
    private Assignment assignment;
    private User submittor;
    private LocalDateTime time;
    private Path submission;
    private String notes;


    public LocalDateTime getTime() {
        return time;
    }

    public Path getSubmission() {
        return submission;
    }

    public String getNotes() {
        return notes;
    }

    public SubmissionCreateRequest(Assignment assignment, User submittor, LocalDateTime time, Path submission, String notes) {

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
