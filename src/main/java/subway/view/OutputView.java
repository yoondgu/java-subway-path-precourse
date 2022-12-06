package subway.view;

import static subway.view.constants.OutputMessage.MAIN_DISPLAY_HEADER;
import static subway.view.constants.OutputMessage.OPTION_DISPLAY_HEADER;

import subway.view.constants.OutputMessage;
import subway.view.constants.menu.MainMenu;
import subway.view.constants.menu.OptionMenu;

public class OutputView {
    private OutputView() {
    }

    public static void printInfoMessage(OutputMessage message) {
        ConsolePrinter.printInfo(message.getValue());
        ConsolePrinter.printBlankLine();
    }

    public static void printErrorMessage(String message) {
        ConsolePrinter.printError(message);
        ConsolePrinter.printBlankLine();
    }

    public static void printMainMenus() {
        ConsolePrinter.printHeader(MAIN_DISPLAY_HEADER.getValue());
        ConsolePrinter.printMenus(MainMenu.values());
        ConsolePrinter.printBlankLine();
    }

    public static void printOptionMenus() {
        ConsolePrinter.printHeader(OPTION_DISPLAY_HEADER.getValue());
        ConsolePrinter.printMenus(OptionMenu.values());
        ConsolePrinter.printBlankLine();
    }
//
//    public static void printSubwayLines(List<PathDTO> allPathsByLine) {
//        ConsolePrinter.printHeader(SUBWAY_LINES_DISPLAY_HEADER.getValue());
//        allPathsByLine.forEach(OutputView::printPathByLine);
//        ConsolePrinter.printBlankLine();
//    }
//
//    private static void printPathByLine(PathDTO path) {
//        ConsolePrinter.printInfo(path.getLineName());
//        ConsolePrinter.printInfo(BORDER_LINE.getValue());
//        path.getStationNames()
//                .forEach(ConsolePrinter::printInfo);
//        ConsolePrinter.printBlankLine();
//    }
}
