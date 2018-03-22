package com.tfowl.lms;

import com.tfowl.lms.model.*;
import com.tfowl.lms.requests.AssignmentCreateRequest;
import com.tfowl.lms.requests.SubmissionCreateRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class LearningManagementSystem {

    private static final Subject MAST30003 = new Subject(UUID.fromString("8749ec58-0b8b-4803-858a-b34dd6adb900"), "MAST30003", "Complex Analysis");
    private static final Subject MAST30036 = new Subject(UUID.fromString("9176d9a6-0a80-4d59-9535-2d42bdb80e1d"), "MAST30036", "Applied Mathematics");
    private static final Subject SWEN30000 = new Subject(UUID.fromString("ded07cac-a339-4197-85d1-eb0a61a0b293"), "SWEN30000", "Software Design");
    private static final Subject COMP30000 = new Subject(UUID.fromString("333faf00-05f5-49b0-8535-8950a555bcb0"), "COMP30000", "Computer System");

    private static final List<User> USERS = Arrays.asList(
            new User(833748, "fowlert@student.unimelb.edu.au", "Thomas", "Fowler", new ArrayList<>(Arrays.asList(
                    new Enrollment(MAST30003, Collections.singletonList(Role.STUDENT)),
                    new Enrollment(MAST30036, Collections.singletonList(Role.STUDENT)),
                    new Enrollment(SWEN30000, Collections.singletonList(Role.STUDENT)),
                    new Enrollment(COMP30000, Collections.singletonList(Role.STUDENT))
            ))),
            new User(1337, "smithj@unimelb.edu.au", "John", "Smith", new ArrayList<>(Collections.singletonList(
                    new Enrollment(SWEN30000, Collections.singletonList(Role.INSTRUCTOR))
            )))
    );

    private static final Map<Integer, String> PASSWORDS = new HashMap<>();

    static {
        PASSWORDS.put(833748, "$2a$10$XXiLkpvRxXV1RjfOaCLmb./x/Rc.fbs84K9.RCYSa5sv1lfOmzg8a");
        PASSWORDS.put(1337, "$2a$10$IgKRqFfUxr6ZSlVaXIdPl.2/ICk2XPim74MHjDiWY4zUxcuhG4rpC");
    }

    public LearningManagementSystem() throws Throwable {
        // Create an assignment randomly
        for (User user : USERS) {
            for (Enrollment enrollment : user.getEnrollments()) {
                if (enrollment.getRoles().contains(Role.INSTRUCTOR)) {
                    AssignmentCreateRequest req = new AssignmentCreateRequest(user,
                            "First assignment!", Paths.get(""), LocalDateTime.now().plusWeeks(1),
                            1, false, HandInMethod.CUSTOM);
                    uploadAssignment(req, enrollment.getSubject());
                    break;
                }
            }
        }
    }

    public Optional<User> login(String email, String password) {
        return USERS.stream().filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .filter(user -> {
                    if (PASSWORDS.containsKey(user.getId())) {
                        return BCrypt.checkpw(password, PASSWORDS.get(user.getId()));
                    } else {
                        return true; //lol?
                    }
                });
    }

    public Assignment uploadAssignment(AssignmentCreateRequest request, Subject subject) {
        boolean hasPermissions = request.getCreator().getEnrollments().stream()
                .filter(enrollment -> enrollment.getSubject().equals(subject))
                .anyMatch(enrollment -> enrollment.getRoles().contains(Role.INSTRUCTOR));
        if (!hasPermissions)
            throw new IllegalStateException("User is not an instructor in this subject and cannot create assignments");

        Assignment assignment = new Assignment(UUID.randomUUID(),
                request.getCreator(), request.getTitle(), request.getInstructionsLocation(), request.getDueDateTime(),
                request.getMaxAttempts(), request.isAllowLateSubmissions(), request.getMethod());
        subject.getAssignments().add(assignment);
        return assignment;
    }

    public SubmissionReceipt uploadSubmission(SubmissionCreateRequest request) {
        boolean hasPermissions = request.getSubmittor().getEnrollments().stream()
                .filter(enrollment -> enrollment.getSubject().getAssignments().contains(request.getAssignment()))
                .anyMatch(enrollment -> enrollment.getRoles().contains(Role.STUDENT));
        if (!hasPermissions)
            throw new IllegalStateException("User is not a student in this subject and cannot submit to an assignment");

        Submission submission = new Submission(UUID.randomUUID(), request.getAssignment(),
                request.getSubmittor(), request.getTime(), request.getSubmission(), request.getNotes());

        SubmissionReceipt receipt = new SubmissionReceipt(submission, "All went well :)");

        request.getSubmittor().getEnrollments().stream()
                .filter(enrollment -> enrollment.getSubject().getAssignments().contains(request.getAssignment()))
                .limit(1)
                .forEach(enrollment -> enrollment.getSubject().getSubmissions().add(receipt));

        return receipt;
    }
}
