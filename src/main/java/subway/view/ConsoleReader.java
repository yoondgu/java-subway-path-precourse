package subway.view;

import static subway.view.constants.ErrorMessage.INPUT_BLANK_NAME;

import java.util.Scanner;
import subway.view.constants.menu.MainCommand;
import subway.view.constants.menu.OptionCommand;

public class ConsoleReader {
    private static ConsoleReader instance;
    private final Scanner scanner;

    private ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public static ConsoleReader getInstance(Scanner scanner) {
        if (instance == null) {
            instance = new ConsoleReader(scanner);
        }
        return instance;
    }

    MainCommand readMainCommand() {
        String line = readLine();
        return MainCommand.find(line);
    }

    OptionCommand readSubCommand() {
        String line = readLine();
        return OptionCommand.find(line);
    }

    String readName() {
        String line = readLine().trim();
        if (line.isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK_NAME.getValue());
        }
        return line;
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
