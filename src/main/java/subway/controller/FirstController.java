package subway.controller;

import static subway.controller.RunStatus.RUNNING;
import static subway.controller.RunStatus.STOPPED;
import static subway.view.constants.menu.MainCommand.QUIT;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import subway.view.InputView;
import subway.view.OutputView;
import subway.view.constants.menu.MainCommand;

public class FirstController {
    private static FirstController instance;
    private static OptionController optionController;
    private static InputView inputView;
    private RunStatus runStatus = STOPPED;

    private FirstController() {
        try {
            run();
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

    public static void initializeInstance(Scanner scanner) {
        if (instance == null) {
            inputView = InputView.getInstance(scanner);
            optionController = OptionController.getInstance(inputView);
            instance = new FirstController();
        }
    }

    private void run() {
        runStatus = RUNNING;
        while (runStatus == RUNNING) {
            ErrorInterceptor.runUntilGetLegalArguments(this::selectMainMenu);
        }
    }

    private void selectMainMenu() {
        OutputView.printMainMenus();
        MainCommand mainCommand = inputView.inputMainCommand();
        runSelectedController(mainCommand);
    }

    private void runSelectedController(MainCommand mainCommand) {
        if (mainCommand == QUIT) {
            runStatus = STOPPED;
            return;
        }
        ControllerHandler.run(mainCommand);
    }

    private enum ControllerHandler {

        SEARCH_ROAD(MainCommand.SEARCH_ROAD, optionController::selectMenu),
        ;

        private static final Map<MainCommand, Runnable> runnableByMainCommand = Arrays.stream(values())
                .collect(Collectors.toMap(value -> value.mainCommand, value -> value.runnable));
        private final MainCommand mainCommand;
        private final Runnable runnable;

        ControllerHandler(MainCommand mainCommand, Runnable runnable) {
            this.mainCommand = mainCommand;
            this.runnable = runnable;
        }

        public static void run(MainCommand command) {
            Runnable controllerMethod = runnableByMainCommand.get(command);
            if (controllerMethod == null) {
                throw new IllegalArgumentException("해당 입력 키워드로 실행할 수 있는 기능이 없습니다.");
            }
            ErrorInterceptor.runUntilGetLegalArguments(controllerMethod);
        }
    }
}
