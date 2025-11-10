package data_structure.graph.backtracking;

public class NQueensSolver {

    public int[][] solve(int n) {
        int[][] board = new int[n][n];

        if (placeQueens(board, 0)) {
            return board;
        }
        return null;
    }

    private boolean placeQueens(int[][] board, int row) {
        int n = board.length;

        if (row >= n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;

                if (placeQueens(board, row + 1)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int[][] board, int row, int col) {
        int n = board.length;

        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }


    public void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No solution found.");
            return;
        }

        int n = board.length;
        for (int[] ints : board) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}
