package data_structure.graph.backtracking;

public class GraphColoringSolver {

    public int[] solve(int[][] graph, int numColors) {
        int n = graph.length;
        int[] colors = new int[n];

        if (colorGraph(graph, numColors, colors, 0)) {
            return colors;
        }
        return null;
    }

    private boolean colorGraph(int[][] graph, int numColors, int[] colors, int node) {
        int n = graph.length;

        if (node == n) {
            return true;
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(graph, colors, node, color)) {
                colors[node] = color;

                if (colorGraph(graph, numColors, colors, node + 1)) {
                    return true;
                }

                colors[node] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int[][] graph, int[] colors, int node, int color) {
        int n = graph.length;

        for (int i = 0; i < n; i++) {
            if (graph[node][i] == 1 && colors[i] == color) {
                return false;
            }
        }
        return true;
    }

    public void printColors(int[] colors) {
        if (colors == null) {
            System.out.println("Graph cannot be colored with the given number of colors.");
            return;
        }

        System.out.println("Colors assigned to nodes:");
        for (int i = 0; i < colors.length; i++) {
            System.out.println("Node " + i + " -> Color " + colors[i]);
        }
    }
}
