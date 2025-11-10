package data_structure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeightedGraph<T> extends AGraph<T> {

    public WeightedGraph(boolean directed){
        super(directed);
    }

    @Override
    public void addEdge(T src, T dest) {
        addEdge(src, dest, DEFAULT_WEIGHT);
    }

    public void addEdge(T src, T dest, int weight) {
        adjMap.get(src).put(dest, weight);
        if(!directed){
            adjMap.get(dest).put(src, weight);
        }
    }

    public int getWeight(T src, T dest){
        return adjMap.getOrDefault(src, Map.of()).getOrDefault(dest, Integer.MAX_VALUE);
    }

    public List<EdgeKruskal<T>> getAllEdges() {
        List<EdgeKruskal<T>> edges = new ArrayList<>();
        for (T src : adjMap.keySet()) {
            for (Map.Entry<T, Integer> entry : adjMap.get(src).entrySet()) {
                T dest = entry.getKey();
                int weight = entry.getValue();

                if (directed || src.hashCode() <= dest.hashCode()) {
                    edges.add(new EdgeKruskal<>(src, dest, weight));
                }
            }
        }
        return edges;
    }

    @Override
    public String formatEdge(T dest, int weight) {
        return dest + "(" + weight + ")";
    }
}
