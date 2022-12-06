package subway.service;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Path;
import subway.domain.PathGroupByLine;
import subway.domain.PathGroupRepository;
import subway.dto.SearchResultDTO;
import subway.dto.SelectedStationsDTO;

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

    public SearchResultDTO searchByDistance(String departureStationName, String arrivalStationName) {
        SearchValidator.validateStations(departureStationName, arrivalStationName);
        WeightedMultigraph<String, DefaultWeightedEdge> graph = generatePathGraphByDistance();

        GraphPath shortestPathByDistance = getShortestPaths(graph, departureStationName, arrivalStationName);
        List<String> paths = shortestPathByDistance.getVertexList();

        SearchValidator.validateSearchedPath(paths);
        double totalDistance = shortestPathByDistance.getWeight();
        double totalTime = computeTotalAmount(generatePathGraphByTime(), paths);
        return new SearchResultDTO(new SelectedStationsDTO(paths), totalDistance, totalTime);
    }

    // TODO 최소 시간 경로 조회

    private double computeTotalAmount(WeightedMultigraph<String, DefaultWeightedEdge> graph, List<String> paths) {
        SearchValidator.validateEdgesInSearchedPath(paths);
        double totalAmount = 0;
        for (int index = 0; index < paths.size() - 1; index++) {
            if (index + 1 > paths.size() - 1) {
                break;
            }
            DefaultWeightedEdge edge = graph.getEdge(paths.get(index), paths.get(index + 1));
            totalAmount += graph.getEdgeWeight(edge);
        }
        return totalAmount;
    }

    private GraphPath getShortestPaths(WeightedMultigraph<String, DefaultWeightedEdge> graph,
                                       String departureStationName,
                                       String arrivalStationName) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        return dijkstraShortestPath.getPath(departureStationName, arrivalStationName);
    }

    private WeightedMultigraph<String, DefaultWeightedEdge> generatePathGraphByDistance() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        PathGroupRepository.getAllLinedStationNames()
                .forEach(graph::addVertex);
        PathGroupRepository.pathGroups()
                .stream().map(PathGroupByLine::getPaths)
                .forEach(paths -> setEdgeWeightByDistance(graph, paths));
        return graph;
    }

    private WeightedMultigraph<String, DefaultWeightedEdge> generatePathGraphByTime() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        PathGroupRepository.getAllLinedStationNames()
                .forEach(graph::addVertex);
        PathGroupRepository.pathGroups()
                .stream().map(PathGroupByLine::getPaths)
                .forEach(paths -> setEdgeWeightByTime(graph, paths));
        return graph;
    }

    private void setEdgeWeightByDistance(WeightedMultigraph<String, DefaultWeightedEdge> graph, List<Path> paths) {
        paths.forEach(path ->
                graph.setEdgeWeight(graph.addEdge(path.get1stStationName(), path.get2ndStationName()),
                        path.getDistance())
        );
    }

    private void setEdgeWeightByTime(WeightedMultigraph<String, DefaultWeightedEdge> graph, List<Path> paths) {
        paths.forEach(path ->
                graph.setEdgeWeight(graph.addEdge(path.get1stStationName(), path.get2ndStationName()), path.getTime())
        );
    }
}
