package subway.service;

import java.util.List;
import subway.domain.PathRepository;
import subway.dto.SearchResultDTO;
import subway.dto.SelectedStationsDTO;

public class SearchService {
    private static SearchService instance;


    private SearchService() {
    }

    public static SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    public SearchResultDTO searchShortestPath(String departureStationName, String arrivalStationName) {
        SearchValidator.validateStations(departureStationName, arrivalStationName);
        List<String> stationsInPath = PathRepository.getShortestPathStations(departureStationName, arrivalStationName);
        SearchValidator.validateSearchedPath(stationsInPath);
        double totalDistance = PathRepository.computeTotalDistanceByStations(stationsInPath);
        double totalTime = PathRepository.computeTotalTimeByStations(stationsInPath);
        return new SearchResultDTO(new SelectedStationsDTO(stationsInPath), totalDistance, totalTime);
    }

    public SearchResultDTO searchFastestPath(String departureStationName, String arrivalStationName) {
        SearchValidator.validateStations(departureStationName, arrivalStationName);
        List<String> stationsInPath = PathRepository.getFastestPathStations(departureStationName, arrivalStationName);
        SearchValidator.validateSearchedPath(stationsInPath);
        double totalDistance = PathRepository.computeTotalDistanceByStations(stationsInPath);
        double totalTime = PathRepository.computeTotalTimeByStations(stationsInPath);
        return new SearchResultDTO(new SelectedStationsDTO(stationsInPath), totalDistance, totalTime);
    }

}
