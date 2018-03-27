package com.tfowl.lms.commands;

import com.tfowl.lms.State;
import com.tfowl.lms.model.Assignment;
import com.tfowl.lms.model.Role;
import com.tfowl.lms.model.Submission;
import com.tfowl.lms.model.SubmissionReceipt;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListSubmissionsCommand extends Command {

	public ListSubmissionsCommand(State state) {
		super("submissions", state);
	}

	@Override
	public boolean exec(String[] args) {
		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("No logged in user.");
			return false;
		}
		if (!getState().getCurrentSubject().isPresent()) {
			System.out.println("No subject currently selected.");
			return false;
		}

		boolean isInstructor = getState().getCurrentUser().get()
									   .getEnrollments().stream()
									   .filter(enrollment -> enrollment.getSubject() == getState().getCurrentSubject().get())
									   .anyMatch(enrollment -> enrollment.getRoles().contains(Role.INSTRUCTOR));

		if (!isInstructor) {
			System.out.println("Current user is not an instructor for the current subject and cannot view submissions.");
			return false;
		}

		getState().getCurrentSubject().ifPresent(subject -> {
			Map<Assignment, List<Submission>> grouped = subject.getSubmissions().stream()
																.map(SubmissionReceipt::getReference)
																.collect(Collectors.groupingBy(Submission::getAssignment));

			if (grouped.isEmpty()) {
				System.out.println("No submissions for any assignments");
				return;
			}

			grouped.forEach(((assignment, submissions) -> {
				System.out.println("Submissions for " + assignment.getTitle());
				for (Submission submission : submissions) {
					System.out.println("\t" + submission.getSubmittor().getEmail() + " AT " + submission.getTime() + " SAYING " + submission.getNotes());
				}
			}));
		});

		return true;
	}
}
