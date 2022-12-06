package subway.controller;

import static subway.controller.RunStatus.RUNNING;
import static subway.controller.RunStatus.STOPPED;
import static subway.view.constants.menu.OptionCommand.BACK;
import static subway.view.constants.menu.OptionCommand.BY_DISTANCE;
import static subway.view.constants.menu.OptionCommand.BY_TIME;

import subway.dto.SearchResultDTO;
import subway.service.SearchService;
import subway.view.InputView;
import subway.view.OutputView;
import subway.view.constants.InputMessage;
import subway.view.constants.menu.OptionCommand;

public class PathSearchController {
    private static PathSearchController instance;
    private final InputView inputView;
    private final SearchService searchService = SearchService.getInstance();

    private PathSearchController(InputView inputView) {
        this.inputView = inputView;
    }

    public static PathSearchController getInstance(InputView inputView) {
        if (instance == null) {
            instance = new PathSearchController(inputView);
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
            searchShortestPath();
        }
        if (command == BY_TIME) {
            searchFastestPath();
        }
        return RUNNING;
    }

    private void searchFastestPath() {
        String departureStationName = inputView.inputName(InputMessage.STATION_TO_DEPART);
        String arrivalStationName = inputView.inputName(InputMessage.STATION_TO_ARRIVE);
        SearchResultDTO searchResultDTO = searchService.searchFastestPath(departureStationName, arrivalStationName);
        OutputView.printSearchResult(searchResultDTO);
    }

    private void searchShortestPath() {
        String departureStationName = inputView.inputName(InputMessage.STATION_TO_DEPART);
        String arrivalStationName = inputView.inputName(InputMessage.STATION_TO_ARRIVE);
        SearchResultDTO searchResultDTO = searchService.searchShortestPath(departureStationName, arrivalStationName);
        OutputView.printSearchResult(searchResultDTO);
    }
}
