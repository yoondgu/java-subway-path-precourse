package subway.domain;

import java.util.List;

public class Path {
    private final List<String> twoStationNames;
    private final int distance;
    private final int time;

    public Path(List<String> twoStationNames, int distance, int time) {
        this.twoStationNames = twoStationNames;
        this.distance = distance;
        this.time = time;
    }

    public String get1stStationName() {
        return twoStationNames.get(0);
    }

    public String get2ndStationName() {
        return twoStationNames.get(1);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public boolean hasStation(String stationName) {
        return twoStationNames.contains(stationName);
    }

    public boolean containsEqual(String departureStationName, String arrivalStationName) {
        if (!twoStationNames.contains(departureStationName)) {
            return false;
        }
        return twoStationNames.contains(arrivalStationName);
    }
}
