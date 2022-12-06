package subway.domain;

import java.util.List;
import java.util.Objects;

public class Path {
    List<String> twoStations;
    int distance;
    int time;

    public Path(List<String> twoStations, int distance, int time) {
        this.twoStations = twoStations;
        this.distance = distance;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Path path = (Path) o;
        return distance == path.distance && time == path.time && twoStations.equals(path.twoStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(twoStations, distance, time);
    }
}
