package subway.domain;

import java.util.List;

public class PathGroupByLine {
    String lineName;
    List<Path> paths;

    public PathGroupByLine(String lineName, List<Path> paths) {
        this.lineName = lineName;
        this.paths = paths;
    }
}
