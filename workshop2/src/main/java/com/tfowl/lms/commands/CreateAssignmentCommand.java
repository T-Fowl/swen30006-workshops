package com.tfowl.lms.commands;

import com.tfowl.lms.Command;
import com.tfowl.lms.State;
import com.tfowl.lms.model.HandInMethod;
import com.tfowl.lms.requests.AssignmentCreateRequest;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class CreateAssignmentCommand extends Command {

	private static final Option OPT_TITLE = Option.builder("t")
													.longOpt("title")
													.hasArg(true)
													.desc("Assignment title")
//													.required(true)
													.build();

	private static final Option OPT_FILE = Option.builder("f")
												   .longOpt("file")
												   .hasArg(true)
												   .desc("Instructions file location")
//												   .required(true)
												   .build();

	private static final Option OPT_DUE = Option.builder("d")
												  .longOpt("due")
												  .hasArg(true)
												  .desc("Due datetime")
//												  .required(true)
												  .build();

	private static final Option OPT_ATTEMPTS = Option.builder("a")
													   .longOpt("attempts")
													   .hasArg(true)
													   .desc("Maximum number of attempts")
													   .optionalArg(true)
													   .build();

	private static final Option OPT_LATE = Option.builder("l")
												   .longOpt("late")
												   .hasArg(false)
												   .desc("Allow late submissions")
												   .optionalArg(true)
												   .build();

	private static final Option OPT_METHOD = Option.builder("m")
													 .longOpt("method")
													 .hasArg(true)
													 .desc("Submission method")
													 .optionalArg(true)
													 .build();

	private static final Option OPT_HELP = new Option("h", "help", false, "Print the command help message");

	private static final Options OPTIONS = new Options()
												   .addOption(OPT_HELP)
												   .addOption(OPT_TITLE)
												   .addOption(OPT_FILE)
												   .addOption(OPT_DUE)
												   .addOption(OPT_ATTEMPTS)
												   .addOption(OPT_LATE)
												   .addOption(OPT_METHOD);

	private DefaultParser parser;

	public CreateAssignmentCommand() {
		super("create");
		parser = new DefaultParser();
	}

	@Override
	public boolean exec(String[] args, State state, Scanner scanner) {
		if (!state.getCurrentUser().isPresent()) {
			System.out.println("Not currently logged in.");
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
				formatter.printHelp(getName() + "<args>", OPTIONS);
				return true;
			}


			AssignmentCreateRequest req = new AssignmentCreateRequest(state.getCurrentUser().get(),
					cmd.getOptionValue(OPT_TITLE.getOpt()),
					Paths.get(cmd.getOptionValue(OPT_FILE.getOpt())),
					LocalDateTime.parse(cmd.getOptionValue(OPT_DUE.getOpt())),
					Integer.parseInt(cmd.getOptionValue(OPT_ATTEMPTS.getOpt(), "1")),
					cmd.hasOption(OPT_LATE.getOpt()),
					HandInMethod.valueOf(cmd.getOptionValue(OPT_METHOD.getOpt(), HandInMethod.FILE_UPLOAD.toString())));

			state.getLms().uploadAssignment(req, state.getCurrentSubject().get());
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}

		return true;
	}
}
