package subway.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import subway.domain.PathGroupRepository;
import subway.domain.StationRepository;

public class SearchValidator {

    private static void validateStationName(String stationName) {
        if (!StationRepository.hasStation(stationName)) {
            throw new IllegalArgumentException("존재하지 않는 역 이름입니다.");
        }
        if (!PathGroupRepository.isLinedStation(stationName)) {
            throw new IllegalArgumentException("노선에 등록되지 않은 역 이름입니다.");
        }
    }

    public static void validateStations(String departureStationName, String arrivalStationNames) {
        validateStationName(departureStationName);
        validateStationName(arrivalStationNames);
        if (Objects.equals(departureStationName, arrivalStationNames)) {
            throw new IllegalArgumentException("출발역과 도착역은 동일할 수 없습니다.");
        }
    }

    public static void validateSearchedPath(List<String> paths) {
        if (paths.isEmpty()) {
            throw new IllegalArgumentException("출발역과 도착역 간 이동 가능한 경로가 존재하지 않습니다.");
        }
    }

    public static void validateEdgesInSearchedPath(List<String> paths) {
        if (isNotConnected(paths)) {
            throw new IllegalArgumentException("계산한 경로가 올바르지 않습니다.");
        }
    }

    private static boolean isNotConnected(List<String> paths) {
        return IntStream.range(0, paths.size() - 2)
                .anyMatch(index -> !PathGroupRepository.hasPath(paths.get(index), paths.get(index + 1)));
    }
}
