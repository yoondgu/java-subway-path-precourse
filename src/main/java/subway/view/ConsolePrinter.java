package subway.view;

import static subway.view.constants.Format.ERROR;
import static subway.view.constants.Format.HEADER;
import static subway.view.constants.Format.INFO;
import static subway.view.constants.Format.MENU;

import java.util.Arrays;
import subway.view.constants.Format;
import subway.view.constants.menu.Menu;

public class ConsolePrinter {
    private ConsolePrinter() {
    }

    static void printBlankLine() {
        System.out.println();
    }

    static void printInfo(String information) {
        ConsolePrinter.printFormattedLine(INFO, information);
    }

    static void printHeader(String message) {
        ConsolePrinter.printFormattedLine(HEADER, message);
    }

    static void printError(String errorMessage) {
        ConsolePrinter.printFormattedLine(ERROR, errorMessage);
    }

    static void printMenus(Menu[] menus) {
        Arrays.stream(menus)
                .forEach(menu -> ConsolePrinter.printFormattedLine(MENU, menu.getCommandKey(), menu.getValue()));
    }

    private static void printFormattedLine(Format format, Object... content) {
        // TODO 출력값 검증
        System.out.printf(format.getValue() + System.lineSeparator(), content);
    }
}
