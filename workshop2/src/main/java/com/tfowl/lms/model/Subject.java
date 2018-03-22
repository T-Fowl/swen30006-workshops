package com.tfowl.lms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Subject {
    private UUID id;
    private String code;
    private String title;
    private List<Assignment> assignments = new ArrayList<>();
    private List<SubmissionReceipt> submissions = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Subject(UUID id, String code, String title) {

       this(id, code, title, new ArrayList<>());
    }

    public Subject(UUID id, String code, String title, List<Assignment> assignments) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.assignments.addAll(assignments);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<SubmissionReceipt> getSubmissions() {
        return submissions;
    }
}
