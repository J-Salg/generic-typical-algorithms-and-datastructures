package data_structure.graph;

import java.util.*;

public class SearchGraph<T>{

    public void depthFirstSearch(Graph<T> graph, T startNode) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            T current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println("DFS visit: " + current);

                List<T> neighbors = new ArrayList<>(graph.getNeighbors(current));
                Collections.reverse(neighbors);
                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void breadthFirstSearch(Graph<T> graph, T startNode) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            System.out.println("BFS visit: " + current);

            for (T neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

}
