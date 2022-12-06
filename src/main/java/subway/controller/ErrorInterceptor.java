package subway.controller;

import subway.view.OutputView;

public class ErrorInterceptor {
    private ErrorInterceptor() {
    }

    public static void runUntilGetLegalArguments(Runnable menuService) {
        while (true) {
            try {
                menuService.run();
                return;
            } catch (IllegalArgumentException exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
