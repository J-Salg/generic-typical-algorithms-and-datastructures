package data_structure.graph;

import java.util.List;

public class FloydWarshall<T>{

    private static final int INF = Integer.MAX_VALUE;

    public void floydWarshallAlg(WeightedGraph<T> graph){
        int n = graph.getNodes().size();
        int[][] dist = new int[n][n];
        List<T> nodes = graph.getNodes();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    dist[i][j] = 0;
                }else{
                    int w = graph.getWeight(nodes.get(i), nodes.get(j));
                    dist[i][j] = (w == Integer.MAX_VALUE) ? INF : w;
                }
            }
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if( dist[i][k] != INF && dist[k][j] != INF &&
                        dist[i][k] + dist[k][j] < dist[i][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        printResult(nodes, dist);

    }

    private void printResult(List<T> nodes, int[][] dist) {
        System.out.println("\nShortest distance matrix:");

        System.out.print("     ");
        for (T node : nodes) {
            System.out.printf("%6s", node);
        }
        System.out.println();

        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%6s", nodes.get(i));
            for (int j = 0; j < dist[i].length; j++) {
                if (dist[i][j] == INF) {
                    System.out.printf("%6s", "∞");
                } else {
                    System.out.printf("%6d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

}
