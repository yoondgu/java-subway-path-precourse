package subway.service;

import static subway.domain.constants.ErrorMessage.DEPART_AND_ARRIVE_SAME;
import static subway.domain.constants.ErrorMessage.PATH_INVALID;
import static subway.domain.constants.ErrorMessage.PATH_NOT_FOUND;
import static subway.domain.constants.ErrorMessage.STATION_NOT_FOUND;
import static subway.domain.constants.ErrorMessage.STATION_NOT_IN_LINE;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import subway.domain.PathRepository;
import subway.domain.StationRepository;

public class SearchValidator {

    private static void validateStationName(String stationName) {
        if (!StationRepository.hasStation(stationName)) {
            throw new IllegalArgumentException(STATION_NOT_FOUND.getValue());
        }
        if (!PathRepository.isLinedStation(stationName)) {
            throw new IllegalArgumentException(STATION_NOT_IN_LINE.getValue());
        }
    }

    public static void validateStations(String departureStationName, String arrivalStationNames) {
        validateStationName(departureStationName);
        validateStationName(arrivalStationNames);
        if (Objects.equals(departureStationName, arrivalStationNames)) {
            throw new IllegalArgumentException(DEPART_AND_ARRIVE_SAME.getValue());
        }
    }

    public static void validateSearchedPath(List<String> paths) {
        if (paths.isEmpty()) {
            throw new IllegalArgumentException(PATH_NOT_FOUND.getValue());
        }
        if (isNotConnected(paths)) {
            throw new IllegalArgumentException(PATH_INVALID.getValue());
        }
    }

    private static boolean isNotConnected(List<String> paths) {
        return IntStream.range(0, (paths.size() - 2))
                .anyMatch(index -> !(PathRepository.hasPath(paths.get(index), paths.get(index + 1))));
    }
}
