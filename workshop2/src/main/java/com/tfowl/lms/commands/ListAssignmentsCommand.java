package com.tfowl.lms.commands;

import com.tfowl.lms.State;

@picocli.CommandLine.Command(description = "List Assignments", name = "assignments")
public class ListAssignmentsCommand extends Command {

	public ListAssignmentsCommand(State state) {
		super("assignments", state);
	}

	@Override
	public Boolean call() throws Exception {
		if (!getState().getCurrentSubject().isPresent()) {
			System.out.println("No subject currently selected.");
			return false;
		}

		getState().getCurrentSubject().ifPresent(subject -> {
			System.out.printf("%s has %d current assignments:%n", subject.getCode(), subject.getAssignments().size());
			subject.getAssignments().forEach(assignment -> {
				System.out.printf("%s (%s) DUE AT %s%n", assignment.getId(), assignment.getTitle(), assignment.getDueDateTime());
			});
		});
		return true;
	}
}
