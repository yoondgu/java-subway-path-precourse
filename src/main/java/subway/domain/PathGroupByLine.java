package subway.domain;

import java.util.List;
import java.util.Objects;

public class PathGroupByLine {
    String lineName;
    List<Path> paths;

    public PathGroupByLine(String lineName, List<Path> paths) {
        this.lineName = lineName;
        this.paths = paths;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public boolean hasPath(String departure, String arrival) {
        return paths.stream()
                .anyMatch(path -> Objects.equals(path.get1stStationName(), departure) &&
                        Objects.equals(path.get2ndStationName(), arrival));
    }
}
