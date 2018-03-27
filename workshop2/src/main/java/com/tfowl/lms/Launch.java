package com.tfowl.lms;

import com.tfowl.lms.commands.*;
import com.tfowl.lms.model.User;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Launch {

	public static void main(String[] args) throws Throwable {
		LearningManagementSystem lms = new LearningManagementSystem();
		State state = new State(lms);

		Scanner scanner = new Scanner(System.in);
		List<Command> registeredCommands = Arrays.asList(
				new CreateAssignmentCommand(state),
				new ListAssignmentsCommand(state),
				new ListSubjectsCommand(state),
				new SelectSubjectCommand(state),
				new SubmitToAssignmentCommand(state),
				new ListSubmissionsCommand(state)
		);

		String line = null;
		while (!(line = scanner.nextLine()).startsWith("q")) {
			String exec = Utils.getCommandExecutable(line);

			if (exec.equalsIgnoreCase("login")) {
				String[] creds = Utils.parseCommandArguments(line);

				if (creds.length < 2) {
					System.out.println("Require 2 arguments, email and password");
					continue;
				}
				Optional<User> maybeUser = lms.login(creds[0], creds[1]);

				if (!maybeUser.isPresent()) {
					System.out.println("Incorrect email or password.");
				} else {
					System.out.println("Successfully logged in as user " + maybeUser.get().getId() + ".");
					state.setCurrentUser(maybeUser.get());
				}

			} else if (exec.equalsIgnoreCase("logout")) {
				state.setCurrentUser(null);
				state.setCurrentSubject(null);
			} else if (exec.equalsIgnoreCase("commands")) {
				System.out.println(registeredCommands.size() + " commands:");
				for (Command command : registeredCommands) {
					System.out.println("\t" + command.getName());
				}
			} else {

				for (Command command : registeredCommands) {
					if (command.getName().equalsIgnoreCase(exec)) {

						Boolean result = CommandLine.call(command, exec);

//						boolean result = command.exec(Utils.parseCommandArguments(line));
						if (!result)
							System.out.println("Command failed.");
						break;
					}
				}
			}
		}

		System.out.println("Bye.");
	}
}
