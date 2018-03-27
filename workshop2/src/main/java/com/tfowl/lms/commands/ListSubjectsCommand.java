package com.tfowl.lms.commands;

import com.tfowl.lms.State;

public class ListSubjectsCommand extends Command {

	public ListSubjectsCommand(State state) {
		super("subjects", state);
	}

	@Override
	public boolean exec(String[] args) {
		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("No user currently logged in.");
			return false;
		}

		getState().getCurrentUser().ifPresent(user -> {
			System.out.printf("User(%s) has %d enrollments:%n", user.getEmail(), user.getEnrollments().size());
			user.getEnrollments().forEach(enrollment -> {
				System.out.printf("\t%s AS %s%n", enrollment.getSubject().getCode(), enrollment.getRoles());
			});
		});
		return true;
	}
}
