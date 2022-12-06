package subway.controller;

import static subway.controller.RunStatus.RUNNING;
import static subway.controller.RunStatus.STOPPED;
import static subway.view.constants.menu.OptionCommand.BACK;
import static subway.view.constants.menu.OptionCommand.BY_DISTANCE;
import static subway.view.constants.menu.OptionCommand.BY_TIME;

import subway.service.SearchService;
import subway.view.InputView;
import subway.view.OutputView;
import subway.view.constants.InputMessage;
import subway.view.constants.menu.OptionCommand;

public class OptionController {
    private static OptionController instance;
    private final InputView inputView;
    private final SearchService searchService = SearchService.getInstance();

    private OptionController(InputView inputView) {
        this.inputView = inputView;
    }

    public static OptionController getInstance(InputView inputView) {
        if (instance == null) {
            instance = new OptionController(inputView);
        }
        return instance;
    }

    void selectMenu() {
        RunStatus runStatus = RUNNING;
        while (runStatus == RUNNING) {
            OutputView.printOptionMenus();
            OptionCommand command = inputView.inputOptionCommand();
            runStatus = runSelectedMenu(command);
        }
    }

    private RunStatus runSelectedMenu(OptionCommand command) {
        if (command == BACK) {
            return STOPPED;
        }
        if (command == BY_DISTANCE) {
            String departureStationName = inputView.inputName(InputMessage.STATION_TO_DEPART);
            String arrivalStationName = inputView.inputName(InputMessage.STATION_TO_ARRIVE);
            System.out.println(searchService.searchByDistance(departureStationName, arrivalStationName));
            // TODO 결과 출력
        }
        if (command == BY_TIME) {
            // TODO 경로 조회
        }
        return RUNNING;
    }

    // TODO 출발/도착역 입력, 최단 거리 경로 조회, 출력
    // TODO 출발/도착역 입력, 최소 시간 경로 조회, 출력
}
