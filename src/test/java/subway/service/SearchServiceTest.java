package subway.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.dto.SearchResultDTO;

class SearchServiceTest {

    private final SearchService searchService = SearchService.getInstance();

    @ParameterizedTest
    @CsvSource(value = {"교대역,양재역,4,11", "매봉역,강남역,3,9"})
    void 최단거리_경로조회(String departureStation, String arrivalStation, int expectedDistance, int expectedTime) {
        SearchResultDTO searchResultDTO = searchService.searchShortestPath(departureStation, arrivalStation);

        assertThat(searchResultDTO.getTotalDistance()).isEqualTo(expectedDistance);
        assertThat(searchResultDTO.getTotalTime()).isEqualTo(expectedTime);
    }

    @ParameterizedTest
    @CsvSource(value = {"교대역,양재역,9,7", "매봉역,강남역,3,9"})
    void 최소시간_경로조회(String departureStation, String arrivalStation, int expectedDistance, int expectedTime) {
        SearchResultDTO searchResultDTO = searchService.searchFastestPath(departureStation, arrivalStation);

        assertThat(searchResultDTO.getTotalDistance()).isEqualTo(expectedDistance);
        assertThat(searchResultDTO.getTotalTime()).isEqualTo(expectedTime);
    }
}