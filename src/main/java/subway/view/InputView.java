package subway.view;

import static subway.view.constants.InputMessage.COMMAND_HEADER;

import java.util.Scanner;
import subway.view.constants.InputMessage;
import subway.view.constants.menu.MainCommand;
import subway.view.constants.menu.OptionCommand;

public class InputView {

    private static InputView instance;
    private final ConsoleReader consoleReader;

    private InputView(Scanner scanner) {
        this.consoleReader = ConsoleReader.getInstance(scanner);
    }

    public static InputView getInstance(Scanner scanner) {
        if (instance == null) {
            instance = new InputView(scanner);
        }
        return instance;
    }

    public MainCommand inputMainCommand() {
        ConsolePrinter.printHeader(COMMAND_HEADER.getValue());
        MainCommand mainCommand = consoleReader.readMainCommand();
        ConsolePrinter.printBlankLine();
        return mainCommand;
    }

    public OptionCommand inputOptionCommand() {
        ConsolePrinter.printHeader(COMMAND_HEADER.getValue());
        OptionCommand optionCommand = consoleReader.readSubCommand();
        ConsolePrinter.printBlankLine();
        return optionCommand;
    }

    public String inputName(InputMessage message) {
        ConsolePrinter.printHeader(message.getValue());
        String name = consoleReader.readName();
        ConsolePrinter.printBlankLine();
        return name;
    }
}
