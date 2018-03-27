package com.tfowl.lms.commands;

import com.tfowl.lms.State;
import com.tfowl.lms.model.Enrollment;

public class SelectSubjectCommand extends Command {

	public SelectSubjectCommand(State state) {
		super("subject", state);
	}

	@Override
	public boolean exec(String[] args) {
		if (args.length < 1) {
			System.out.println("Requires 1 argument, the subject code.");
			return false;
		}

		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("Not logged in.");
			return false;
		}

		String subject = args[0];
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
