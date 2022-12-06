package subway;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.Path;
import subway.domain.PathGroupByLine;
import subway.domain.PathGroupRepository;

public class JGraphtTest {
    private static void setEdgeWeightByDistance(WeightedMultigraph<String, DefaultWeightedEdge> graph,
                                                List<Path> paths) {
        paths.forEach(path -> {
            graph.setEdgeWeight(graph.addEdge(path.get1stStationName(), path.get2ndStationName()), path.getDistance());
        });
    }

    private static void setEdgeWeightByTime(WeightedMultigraph<String, DefaultWeightedEdge> graph, List<Path> paths) {
        paths.forEach(path -> {
            graph.setEdgeWeight(graph.addEdge(path.get1stStationName(), path.get2ndStationName()), path.getTime());
        });
    }

    private static WeightedMultigraph<String, DefaultWeightedEdge> generatePathGraphByDistance() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        PathGroupRepository.getAllLinedStationNames()
                .forEach(graph::addVertex);
        PathGroupRepository.pathGroups()
                .stream().map(PathGroupByLine::getPaths)
                .forEach(paths -> setEdgeWeightByDistance(graph, paths));
        return graph;
    }

    private static WeightedMultigraph<String, DefaultWeightedEdge> generatePathGraphByTime() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        PathGroupRepository.getAllLinedStationNames()
                .forEach(graph::addVertex);
        PathGroupRepository.pathGroups()
                .stream().map(PathGroupByLine::getPaths)
                .forEach(paths -> setEdgeWeightByTime(graph, paths));
        return graph;
    }

    private static double calculateTotalTime(List<String> paths) {
        WeightedMultigraph<String, DefaultWeightedEdge> graphByTime = generatePathGraphByTime();
        double totalTime = 0;
        for (int index = 0; index < paths.size() - 1; index++) {
            if (index + 1 > paths.size() - 1) {
                break;
            }
            DefaultWeightedEdge edge = graphByTime.getEdge(paths.get(index), paths.get(index + 1));
            totalTime += graphByTime.getEdgeWeight(edge);
        }
        return totalTime;
    }

    private static double calculateTotalDistance(List<String> paths) {
        WeightedMultigraph<String, DefaultWeightedEdge> graphByDistance = generatePathGraphByDistance();
        double totalDistance = 0;
        for (int index = 0; index < paths.size() - 1; index++) {
            if (index + 1 > paths.size() - 1) {
                break;
            }
            DefaultWeightedEdge edge = graphByDistance.getEdge(paths.get(index), paths.get(index + 1));
            totalDistance += graphByDistance.getEdgeWeight(edge);
        }
        return totalDistance;
    }

    @Test
    public void getDijkstraShortestPath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addVertex("v3");
        graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
        graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
        graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();

        assertThat(shortestPath.size()).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"교대역,양재역,3", "매봉역,강남역,3"})
    public void 사전등록정보_최단거리경로(String departureStation, String arrivalStation, int expectedDistance) {
        WeightedMultigraph<String, DefaultWeightedEdge> graphByDistance = generatePathGraphByDistance();
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graphByDistance);
        GraphPath shortestPathByDistance = dijkstraShortestPath.getPath(departureStation, arrivalStation);
        List<String> paths = shortestPathByDistance.getVertexList();
        double totalDistance = dijkstraShortestPath.getPath(departureStation, arrivalStation).getWeight();

        System.out.println(paths);
        System.out.println(totalDistance);
        System.out.println(calculateTotalTime(paths));

        assertThat(paths.size()).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @CsvSource(value = {"교대역,양재역,3", "매봉역,강남역,3"})
    public void 사전등록정보_최소시간경로(String departureStation, String arrivalStation, int expectedDistance) {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = generatePathGraphByTime();
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        GraphPath shortestPathByDistance = dijkstraShortestPath.getPath(departureStation, arrivalStation);
        List<String> paths = shortestPathByDistance.getVertexList();
        double totalTime = dijkstraShortestPath.getPath(departureStation, arrivalStation).getWeight();

        System.out.println(paths);
        System.out.println(totalTime);
        System.out.println(calculateTotalDistance(paths));

        assertThat(paths.size()).isEqualTo(expectedDistance);
    }
}
