package com.tfowl.lms.commands;

import com.tfowl.lms.State;
import com.tfowl.lms.model.Enrollment;
import picocli.CommandLine;

public class SelectSubjectCommand extends Command {

	@CommandLine.Parameters(index = "0", description = "Subject to select")
	private String subject;

	public SelectSubjectCommand(State state) {
		super("subject", state);
	}

	@Override
	public boolean exec() {
		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("Not logged in.");
			return false;
		}

		getState().getCurrentUser().ifPresent(user -> {
			boolean didFind = false;
			for (Enrollment enrollment : user.getEnrollments()) {
				if (enrollment.getSubject().getCode().equalsIgnoreCase(subject)) {
					getState().setCurrentSubject(enrollment.getSubject());
					didFind = true;
					break;
				}
			}
			if (!didFind) {
				System.out.println("No matching subject found for: " + subject);
			}
		});
		return true;
	}
}
