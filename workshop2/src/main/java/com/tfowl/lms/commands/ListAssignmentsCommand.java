package com.tfowl.lms.commands;

import com.tfowl.lms.Command;
import com.tfowl.lms.State;

import java.util.Scanner;

public class ListAssignmentsCommand extends Command {

    public ListAssignmentsCommand() {
        super("assignments");
    }

    @Override
    public boolean exec(String[] args, State state, Scanner scanner) {
        if (!state.getCurrentSubject().isPresent()) {
            System.out.println("No subject currently selected.");
            return false;
        }

        state.getCurrentSubject().ifPresent(subject -> {
            System.out.printf("%s has %d current assignments:%n", subject.getCode(), subject.getAssignments().size());
            subject.getAssignments().forEach(assignment -> {
                System.out.printf("%s (%s) DUE AT %s%n", assignment.getId(), assignment.getTitle(), assignment.getDueDateTime());
            });
        });
        return true;
    }
}
