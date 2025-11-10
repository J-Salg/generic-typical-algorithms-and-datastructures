package data_structure.graph;

import java.util.List;

public class Warshall<T> {

    public void warshallAlg(Graph<T> graph){

        List<T> nodes = graph.getNodes();
        int n = nodes.size();
        int[][] closure = new int[n][n];

        for(int i = 0; i < n; i++){
            T from = nodes.get(i);
            for(int j = 0; j < n; j++){
                T to = nodes.get(j);
                closure[i][j] = graph.getNeighbors(from).contains(to) ? 1 : 0;
            }
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    closure[i][j] = closure[i][j] | (closure[i][k] & closure[k][j]);
                }
            }
        }

        printClosureMatrix(nodes, closure);
    }

    private void printClosureMatrix(List<T> nodes, int[][] closure) {
        System.out.println("\nTransitive closure:");
        System.out.print("     ");
        for (T node : nodes) {
            System.out.printf("%4s", node);
        }
        System.out.println();

        for (int i = 0; i < closure.length; i++) {
            System.out.printf("%4s", nodes.get(i));
            for (int j = 0; j < closure[i].length; j++) {
                System.out.printf("%4d", closure[i][j]);
            }
            System.out.println();
        }
    }
}
