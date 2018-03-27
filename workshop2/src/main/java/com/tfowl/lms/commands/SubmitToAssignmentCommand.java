package com.tfowl.lms.commands;

import com.tfowl.lms.State;
import com.tfowl.lms.model.Assignment;
import com.tfowl.lms.model.SubmissionReceipt;
import com.tfowl.lms.requests.SubmissionCreateRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class SubmitToAssignmentCommand extends Command {


	@picocli.CommandLine.Option(names = {"-a", "--assignment"}, required = true, description = "ID of the assignment")
	private UUID assignment;
	@picocli.CommandLine.Option(names = {"-f", "--file"}, required = true, description = "Submission file")
	private File file;
	@picocli.CommandLine.Option(names = {"-n", "--notes"}, required = false, description = "Optional notes for submission")
	private String notes = "";

	public SubmitToAssignmentCommand(State state) {
		super("submit", state);
	}

	@Override
	public boolean exec() {
		if (!getState().getCurrentUser().isPresent()) {
			System.out.println("Not logged in.");
			return false;
		}
		if (!getState().getCurrentSubject().isPresent()) {
			System.out.println("No subject currently selected.");
			return false;
		}


		Optional<Assignment> assignment = getState().getCurrentSubject()
												  .flatMap(subject ->
																   subject.getAssignments().stream()
																		   .filter(a -> a.getId().equals(SubmitToAssignmentCommand.this.assignment))
																		   .findFirst()
												  );

		if (!assignment.isPresent()) {
			System.out.println("Could not find assignment with id " + assignment);
			return false;
		}

		SubmissionCreateRequest req = new SubmissionCreateRequest(assignment.get(),
				getState().getCurrentUser().get(),
				LocalDateTime.now(),
				file.toPath(),
				notes);

		SubmissionReceipt receipt = getState().getLms().uploadSubmission(req);
		System.out.println("Receipt: " + receipt.getReference().getId() + ": " + receipt.getMessage());
		return true;
	}
}
