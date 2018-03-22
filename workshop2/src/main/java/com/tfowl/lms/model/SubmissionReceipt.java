package com.tfowl.lms.model;

public class SubmissionReceipt {
    private Submission reference;
    private String message;

    public Submission getReference() {
        return reference;
    }

    public String getMessage() {
        return message;
    }

    public SubmissionReceipt(Submission reference, String message) {

        this.reference = reference;
        this.message = message;
    }
}
