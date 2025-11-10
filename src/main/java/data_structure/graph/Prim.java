package data_structure.graph;

import java.util.*;

public class Prim<T> {

    public void primMST(WeightedGraph<T> graph){
        List<T> nodes = graph.getNodes();
        int n = nodes.size();

        Map<T, Integer> key = new HashMap<>();
        Map<T, T> parent = new HashMap<>();
        Set<T> inMST = new HashSet<>();

        for(T node : nodes){
            key.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }

        T start = nodes.getFirst();
        key.put(start, 0);

        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(key::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            T u = pq.poll();

            if (inMST.contains(u)) continue;
            inMST.add(u);

            for (T v : graph.getNeighbors(u)) {
                int weight = graph.getWeight(u, v);
                if (!inMST.contains(v) && weight < key.get(v)) {
                    key.put(v, weight);
                    parent.put(v, u);
                    pq.add(v);
                }
            }
        }

        printResult(parent, key, nodes);
    }

    private void printResult(Map<T, T> parent, Map<T, Integer> key, List<T> nodes) {
        System.out.println("\nMinimum Spanning Tree:");
        int totalWeight = 0;

        for (T node : nodes) {
            T p = parent.get(node);
            if (p != null) {
                int w = key.get(node);
                totalWeight += w;
                System.out.println(p + " - " + node + " : " + w);
            }
        }

        System.out.println("Total weight: " + totalWeight);
    }

}
