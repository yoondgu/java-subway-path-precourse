package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathRepository {
    private static final List<Path> paths = new ArrayList<>();
    private static final PathGraph pathGraphByDistance = new PathGraph();
    private static final PathGraph pathGraphByTime = new PathGraph();

    static {
        paths.add(new Path(List.of("교대역", "강남역"), 2, 3));
        paths.add(new Path(List.of("강남역", "역삼역"), 2, 3));
        paths.add(new Path(List.of("교대역", "남부터미널역"), 3, 2));
        paths.add(new Path(List.of("남부터미널역", "양재역"), 6, 5));
        paths.add(new Path(List.of("양재역", "매봉역"), 1, 1));
        paths.add(new Path(List.of("강남역", "양재역"), 2, 8));
        paths.add(new Path(List.of("양재역", "양재시민의숲역"), 10, 3));

        paths.forEach(path -> pathGraphByDistance.insertPath(path.get1stStationName(), path.get2ndStationName(),
                path.getDistance()));
        paths.forEach(
                path -> pathGraphByTime.insertPath(path.get1stStationName(), path.get2ndStationName(), path.getTime()));
    }

    public static List<Path> paths() {
        return Collections.unmodifiableList(paths);
    }

    public static void addPath(Path path) {
        paths.add(path);
        pathGraphByDistance.insertPath(path.get1stStationName(), path.get2ndStationName(), path.getDistance());
        pathGraphByTime.insertPath(path.get1stStationName(), path.get2ndStationName(), path.getTime());
    }

    public static void deletePath(String departureStationName, String arrivalStationName) {
        paths.removeIf(path -> path.containsEqual(departureStationName, arrivalStationName));
        // TODO graph 에 해당하는 edge 삭제
    }

    public static boolean isLinedStation(String stationNames) {
        return paths().stream()
                .anyMatch(path -> path.hasStation(stationNames));
    }

    public static boolean hasPath(String departureStationName, String arrivalStationName) {
        return paths.stream()
                .anyMatch(path -> path.containsEqual(departureStationName, arrivalStationName));
    }

    public static List<String> getShortestPathStations(String departureStationName, String arrivalStationName) {
        return pathGraphByDistance.getShortestPath(departureStationName, arrivalStationName);
    }

    public static List<String> getFastestPathStations(String departureStationName, String arrivalStationName) {
        return pathGraphByTime.getShortestPath(departureStationName, arrivalStationName);
    }

    public static double computeTotalDistanceByStations(List<String> stations) {
        return pathGraphByDistance.computeTotalWeightByPathStations(stations);
    }

    public static double computeTotalTimeByStations(List<String> stations) {
        return pathGraphByTime.computeTotalWeightByPathStations(stations);
    }
}
