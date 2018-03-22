package com.tfowl.lms.commands;

import com.tfowl.lms.Command;
import com.tfowl.lms.State;
import com.tfowl.lms.model.Assignment;
import com.tfowl.lms.model.SubmissionReceipt;
import com.tfowl.lms.requests.SubmissionCreateRequest;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class SubmitToAssignmentCommand extends Command {


	private static final Option OPT_ASSIGNMENT = Option.builder("a")
														 .longOpt("assignment")
														 .hasArg(true)
														 .desc("Id of the assignment")
//														 .required(true)
														 .build();

	private static final Option OPT_FILE = Option.builder("f")
												   .longOpt("file")
												   .hasArg(true)
												   .desc("File to upload / send / etc")
//												   .required(true)
												   .build();

	private static final Option OPT_NOTES = Option.builder("n")
													.longOpt("notes")
													.hasArg(true)
													.desc("Notes for submission")
													.optionalArg(true)
													.build();

	private static final Option OPT_HELP = new Option("h", "help", false, "Print this help message.");

	private static final Options OPTIONS = new Options()
												   .addOption(OPT_HELP)
												   .addOption(OPT_ASSIGNMENT)
												   .addOption(OPT_FILE)
												   .addOption(OPT_NOTES);

	private DefaultParser parser = new DefaultParser();


	public SubmitToAssignmentCommand() {
		super("submit");
	}

	@Override
	public boolean exec(String[] args, State state, Scanner scanner) {
		if (!state.getCurrentUser().isPresent()) {
			System.out.println("Not logged in.");
			return false;
		}
		if (!state.getCurrentSubject().isPresent()) {
			System.out.println("No subject currently selected.");
			return false;
		}

		try {
			CommandLine cmd = parser.parse(OPTIONS, args);

			if (cmd.hasOption(OPT_HELP.getOpt())) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(getName() + " <args>", OPTIONS);
				return true;
			}

			String id = cmd.getOptionValue(OPT_ASSIGNMENT.getOpt());

			Optional<Assignment> assignment = state.getCurrentSubject()
													  .flatMap(subject ->
																	   subject.getAssignments().stream()
																			   .filter(a -> a.getId().toString().equalsIgnoreCase(id))
																			   .findFirst()
													  );

			if (!assignment.isPresent()) {
				System.out.println("Could not find assignment with id " + id);
				return false;
			}

			SubmissionCreateRequest req = new SubmissionCreateRequest(assignment.get(),
					state.getCurrentUser().get(),
					LocalDateTime.now(),
					Paths.get(cmd.getOptionValue(OPT_FILE.getOpt())),
					cmd.getOptionValue(OPT_NOTES.getOpt(), ""));

			SubmissionReceipt receipt = state.getLms().uploadSubmission(req);
			System.out.println("Receipt: " + receipt.getReference().getId() + ": " + receipt.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
