import java.util.*;

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                }
            }
        }
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int size = q.size();
            boolean changed = false;
            for (int j = 0; j < size; j++) {
                int r = q.peek().first;
                int c = q.peek().second;
                q.remove();

                for (int i = 0; i < 4; i++) {
                    int nr = r + delrow[i];
                    int nc = c + delcol[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    q.add(new Pair(nr, nc));
                    changed = true;
                    }
                }
            }
            if (changed) {
                ans++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return ans;
    }
}