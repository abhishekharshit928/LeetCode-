class Solution {

    int[] delrow = {-1, 1, 0, 0};
    int[] delcol = {0, 0, -1, 1};

    private void traversal(int i, int j, boolean[][] vis, int[][] grid) {

        vis[i][j] = true;

        int n = grid.length;
        int m = grid[0].length;

        for (int k = 0; k < 4; k++) {

            int nrow = i + delrow[k];
            int ncol = j + delcol[k];

            if (nrow >= 0 && nrow < n &&
                ncol >= 0 && ncol < m &&
                !vis[nrow][ncol] && grid[nrow][ncol] == 1) {

                traversal(nrow, ncol, vis, grid);
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] vis = new boolean[m][n];

        // First and last columns
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1 && !vis[i][0]) {
                traversal(i, 0, vis, grid);
            }

            if (grid[i][n-1] == 1 && !vis[i][n-1]) {
                traversal(i, n-1, vis, grid);
            }
        }

        // First and last rows
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1 && !vis[0][j]) {
                traversal(0, j, vis, grid);
            }

            if (grid[m-1][j] == 1 && !vis[m-1][j]) {
                traversal(m-1, j, vis, grid);
            }
        }

        // Count remaining land cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    ans++;
                }
            }
        }

        return ans;
    }
}