package subway.service;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Path;
import subway.domain.PathGroupByLine;
import subway.domain.PathGroupRepository;

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
}
