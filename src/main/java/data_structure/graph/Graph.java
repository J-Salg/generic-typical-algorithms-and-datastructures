package data_structure.graph;

import java.util.List;

public interface Graph<T> {
    void addNode(T node);
    void addEdge(T src, T dest);

    List<T> getNeighbors(T node);
    List<T> getNodes();

    void printGraph();
}
