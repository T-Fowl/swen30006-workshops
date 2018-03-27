package com.tfowl.lms.commands;

import com.tfowl.lms.State;
import com.tfowl.lms.model.HandInMethod;
import com.tfowl.lms.requests.AssignmentCreateRequest;

import java.io.File;
import java.time.LocalDateTime;

@picocli.CommandLine.Command(description = "Create Assignment", name = "create")
public class CreateAssignmentCommand extends Command {

	@picocli.CommandLine.Option(names = {"-t", "--title"}, required = true, description = "Assignment title")
	private String title;

	@picocli.CommandLine.Option(names = {"-f", "--file"}, required = true, description = "Instructions file location")
	private File file;

	@picocli.CommandLine.Option(names = {"-d", "--due"}, required = true, description = "Due datetime")
	private LocalDateTime due;

	@picocli.CommandLine.Option(names = {"-a", "--attempts"}, description = "Maximum number of attempts")
	private int attempts = 1;

	@picocli.CommandLine.Option(names = {"-l", "--late"}, description = "Allow late submissions")
	private boolean allowLate = false;

	@picocli.CommandLine.Option(names = {"-m", "--method"}, description = "Submission method")
	private HandInMethod method = HandInMethod.CUSTOM;

	public CreateAssignmentCommand(State state) {
		super("create", state);
	}

	@Override
	public Boolean call() throws Exception {
		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("Not currently logged in.");
			return false;
		}
		if (!getState().getCurrentSubject().isPresent()) {
			System.out.println("No subject currently selected.");
			return false;
		}

		try {

			AssignmentCreateRequest req = new AssignmentCreateRequest(getState().getCurrentUser().get(),
					title,
					file.toPath(),
					due,
					attempts,
					allowLate,
					method);

			getState().getLms().uploadAssignment(req, getState().getCurrentSubject().get());
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}

		return true;
	}
}
