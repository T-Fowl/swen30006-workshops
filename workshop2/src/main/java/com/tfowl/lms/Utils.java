package com.tfowl.lms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String getCommandExecutable(String command) {
        command = command.trim();
        String[] parts = command.split("\\s+");
        return parts[0];
    }

    public static String[] parseCommandArguments(String command) {
        List<String> parts = Arrays.asList(command.trim().split("\\s+"));
        List<String> cleaned = new ArrayList<>(parts);
        cleaned.remove(0);

        return cleaned.toArray(new String[0]);
    }
}
