package data_structure.graph.backtracking;


public class MazeSolver {

    private int N;

    public int[][] solve(int[][] maze, int startX, int startY, int endX, int endY) {
        this.N = maze.length;
        int[][] solution = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        if (solveMaze(maze, startX, startY, endX, endY, solution, visited)) {
            return solution;
        }
        return null;
    }

    private boolean solveMaze(int[][] maze, int x, int y, int endX, int endY, int[][] solution, boolean[][] visited) {
        if (x == endX && y == endY && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y) && !visited[x][y]) {
            visited[x][y] = true;
            solution[x][y] = 1;

            if (solveMaze(maze, x + 1, y, endX, endY, solution, visited)) {
                return true;
            }

            if (solveMaze(maze, x, y + 1, endX, endY, solution, visited)) {
                return true;
            }

            if (solveMaze(maze, x - 1, y, endX, endY, solution, visited)) {
                return true;
            }

            if (solveMaze(maze, x, y - 1, endX, endY, solution, visited)) {
                return true;
            }

            solution[x][y] = 0;
            return false;
        }

        return false;
    }

    private boolean isSafe(int[][] maze, int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
    }

    public void printSolution(int[][] solution) {
        if (solution == null) {
            System.out.println("No solution exists for the maze.");
            return;
        }

        System.out.println("Solution Path:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}