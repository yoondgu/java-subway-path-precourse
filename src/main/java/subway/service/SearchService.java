package subway.service;

public class SearchService {
    private static SearchService instance;

    private SearchService() {
    }

    public static SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    // TODO 최단 거리 경로 조회
    // TODO 최소 시간 경로 조회
}
