package data_structure.graph;

import java.util.*;

public class Kruskal<T> {
    public void kruskalMST(WeightedGraph<T> graph) {
        List<EdgeKruskal<T>> edges = graph.getAllEdges();
        Collections.sort(edges);

        List<T> nodes = graph.getNodes();
        Map<T, T> parent = new HashMap<>();
        Map<T, Integer> rank = new HashMap<>();

        for (T node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }

        List<EdgeKruskal<T>> mst = new ArrayList<>();

        for (EdgeKruskal<T> edge : edges) {
            T rootU = find(parent, edge.getSrc());
            T rootV = find(parent, edge.getDest());

            if (!rootU.equals(rootV)) {
                mst.add(edge);
                union(parent, rank, rootU, rootV);
            }

            if (mst.size() == nodes.size() - 1) break;
        }

        printResult(mst);
    }

    private T find(Map<T, T> parent, T node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent, parent.get(node)));
        }
        return parent.get(node);
    }

    private void union(Map<T, T> parent, Map<T, Integer> rank, T u, T v) {
        T rootU = find(parent, u);
        T rootV = find(parent, v);

        int rankU = rank.get(rootU);
        int rankV = rank.get(rootV);

        if (rankU < rankV) {
            parent.put(rootU, rootV);
        } else if (rankU > rankV) {
            parent.put(rootV, rootU);
        } else {
            parent.put(rootV, rootU);
            rank.put(rootU, rankU + 1);
        }
    }

    private void printResult(List<EdgeKruskal<T>> mst) {
        System.out.println("\nMinimum Spanning Tree (Kruskal):");
        int totalWeight = 0;
        for (EdgeKruskal<T> edge : mst) {
            System.out.println(edge);
            totalWeight += edge.getWeight();
        }
        System.out.println("Total weight: " + totalWeight);
    }
}
