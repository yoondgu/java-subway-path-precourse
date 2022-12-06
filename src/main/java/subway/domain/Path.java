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

    public String get1stStationName() {
        return twoStations.get(0);
    }

    public String get2ndStationName() {
        return twoStations.get(1);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
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
