package data_structure.graph;

import java.util.*;

public class FordFulkerson<T> {

    public int fordFulkerson(WeightedGraph<T> graph, T source, T sink) {
        List<T> nodes = graph.getNodes();
        int n = nodes.size();

        Map<T, Map<T, Integer>> residual = new HashMap<>();
        for (T u : nodes) {
            residual.put(u, new HashMap<>());
            for (T v : graph.getNeighbors(u)) {
                residual.get(u).put(v, graph.getWeight(u, v));
            }
        }

        Map<T, T> parent = new HashMap<>();
        int maxFlow = 0;

        while (bfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (T v = sink; !v.equals(source); v = parent.get(v)) {
                T u = parent.get(v);
                pathFlow = Math.min(pathFlow, residual.get(u).getOrDefault(v, 0));
            }

            for (T v = sink; !v.equals(source); v = parent.get(v)) {
                T u = parent.get(v);
                residual.get(u).put(v, residual.get(u).get(v) - pathFlow);
                residual.get(v).put(u, residual.getOrDefault(v, new HashMap<>())
                        .getOrDefault(u, 0) + pathFlow);
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private boolean bfs(Map<T, Map<T, Integer>> residual, T source, T sink, Map<T, T> parent) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(source);
        visited.add(source);
        parent.clear();

        while (!queue.isEmpty()) {
            T u = queue.poll();

            for (Map.Entry<T, Integer> entry : residual.getOrDefault(u, Map.of()).entrySet()) {
                T v = entry.getKey();
                int capacity = entry.getValue();

                if (!visited.contains(v) && capacity > 0) {
                    parent.put(v, u);
                    if (v.equals(sink)) {
                        return true;
                    }
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return false;
    }
}
