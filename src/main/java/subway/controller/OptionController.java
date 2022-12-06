package subway.controller;

import static subway.controller.RunStatus.RUNNING;
import static subway.controller.RunStatus.STOPPED;
import static subway.view.constants.menu.OptionCommand.BACK;
import static subway.view.constants.menu.OptionCommand.BY_DISTANCE;
import static subway.view.constants.menu.OptionCommand.BY_TIME;

import subway.view.constants.menu.OptionCommand;

public class OptionController {

    private OptionController() {
    }

    static void selectMenu() {
        RunStatus runStatus = RUNNING;
//        while (runStatus == RUNNING) {
//            OutputView.printPathMenus();
//            SubCommand command = InputView.inputSubCommand();
//            runStatus = runSelectedMenu(command);
//        }
    }

    private static RunStatus runSelectedMenu(OptionCommand command) {
        if (command == BACK) {
            return STOPPED;
        }
        if (command == BY_DISTANCE) {
            // TODO 경로 조회
        }
        if (command == BY_TIME) {
            // TODO 경로 조회
        }
        return RUNNING;
    }
}
