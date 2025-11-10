package data_structure.graph;

import java.util.*;

public class Dijkstra<T> {

    private static final int INF = Integer.MAX_VALUE;

    public void dijkstraAlg(WeightedGraph<T> graph, T startNode){
        Map<T, Integer> dist = new HashMap<>();
        Map<T, T> prev = new HashMap<>();

        for(T node : graph.getNodes()){
            dist.put(node, INF);
        }
        dist.put(startNode, 0);

        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(startNode);

        while (!pq.isEmpty()){
            T current = pq.poll();

            for(T neighbor : graph.getNeighbors(current)){
                int weight = graph.getWeight(current, neighbor);
                if(weight < 0){
                    continue;
                }

                int newDist = dist.get(current) + weight;
                if(newDist < dist.get(neighbor)){
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        printResults(startNode, dist, prev);
    }

    private void printResults(T startNode, Map<T, Integer> dist, Map<T, T> prev) {
        System.out.println("\nMinimum distances from " + startNode + " (Dijkstra):");
        System.out.println("Node\tCost");
        for (T node : dist.keySet()) {
            int d = dist.get(node);
            if (d == INF) {
                System.out.println(node + "\t\t∞");
            } else {
                System.out.printf("%s\t\t%d\t\tPath: %s%n", node, d, reconstructPath(node, prev));
            }
        }
    }

    private List<T> reconstructPath(T target, Map<T, T> prev) {
        LinkedList<T> path = new LinkedList<>();
        for (T at = target; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

}
