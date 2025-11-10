package data_structure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AGraph<T> implements Graph<T>{

    protected final Map<T, Map<T, Integer>> adjMap = new HashMap<>();
    protected final boolean directed;
    protected static final int DEFAULT_WEIGHT = 1;

    public AGraph(boolean directed){
        this.directed = directed;
    }

    public void addNode(T node){
        adjMap.putIfAbsent(node, new HashMap<>());
    }

    public List<T> getNeighbors(T node){
        return new ArrayList<>(adjMap.getOrDefault(node, Map.of()).keySet());
    }

    public List<T> getNodes(){
        return new ArrayList<>(adjMap.keySet());
    }

    public void printGraph(){
        for(var entry : adjMap.entrySet()){
            System.out.print(entry.getKey() + " -> ");
            entry.getValue().forEach((dest, w) -> System.out.print(formatEdge(dest, w) + " "));
            System.out.println();
        }
    }

    public abstract String formatEdge(T dest, int weight);

}
