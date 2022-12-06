package subway.dto;

public class SearchResultDTO {
    private final SelectedStationsDTO selectedStationsDTO;
    private final double totalDistance;
    private final double totalTime;

    public SearchResultDTO(SelectedStationsDTO selectedStationsDTO, double totalDistance, double totalTime) {
        this.selectedStationsDTO = selectedStationsDTO;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public SelectedStationsDTO getSelectedStationsDTO() {
        return selectedStationsDTO;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalTime() {
        return totalTime;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "selectedStationsDTO=" + selectedStationsDTO +
                ", totalDistance=" + totalDistance +
                ", totalTime=" + totalTime +
                '}';
    }
}
