package com.tfowl.lms;

import java.util.Scanner;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean exec(String[] args, State state, Scanner scanner);

    public String getName() {
        return name;
    }
}
