package com.tfowl.lms.commands;

import com.tfowl.lms.Command;
import com.tfowl.lms.State;

import java.util.Scanner;

public class ListSubjectsCommand extends Command {

    public ListSubjectsCommand() {
        super("subjects");
    }

    @Override
    public boolean exec(String[] args, State state, Scanner scanner) {
        if(!state.getCurrentUser().isPresent()) {
            System.out.println("No user currently logged in.");
            return false;
        }

        state.getCurrentUser().ifPresent(user -> {
            System.out.printf("User(%s) has %d enrollments:%n", user.getEmail(), user.getEnrollments().size());
            user.getEnrollments().forEach(enrollment -> {
                System.out.printf("\t%s AS %s%n", enrollment.getSubject().getCode(), enrollment.getRoles());
            });
        });
        return true;
    }
}
