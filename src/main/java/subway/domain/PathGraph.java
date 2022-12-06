package subway.domain;

import java.util.List;
import java.util.stream.IntStream;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathGraph {
    private final WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph<>(
            DefaultWeightedEdge.class);

    public void insertPath(String srcStation, String distStation, int distance) {
        setVertex(srcStation, distStation);
        setEdge(srcStation, distStation, distance);
    }

    private void setVertex(String src, String dist) {
        graph.addVertex(src);
        graph.addVertex(dist);
    }

    private void setEdge(String src, String dist, int weight) {
        graph.setEdgeWeight(graph.addEdge(src, dist), weight);
    }

    public List<String> getShortestPath(String srcStation, String distStation) {
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        return shortestPath.getPath(srcStation, distStation).getVertexList();
    }

    public double computeTotalWeightByPathStations(List<String> stations) {
        return IntStream.range(0, (stations.size() - 1))
                .mapToDouble(index -> graph.getEdgeWeight(graph.getEdge(stations.get(index), stations.get(index + 1))))
                .reduce(0.0, Double::sum);
    }
}
