package subway.dto;

import java.util.List;

public class SelectedStationsDTO {
    private final List<String> selectedStations;

    public SelectedStationsDTO(List<String> selectedStations) {
        this.selectedStations = selectedStations;
    }

    public List<String> getSelectedStations() {
        return selectedStations;
    }
}
