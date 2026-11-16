class Pair {
    int time;
    int row;
    int col;

    Pair(int t, int r, int c) {
        this.time = t;
        this.row = r;
        this.col = c;
    }
}

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++) {
           Arrays.fill(dist[i], Integer.MAX_VALUE);
       }

       dist[0][0] = grid[0][0];
       PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->x.time-y.time);
       pq.add(new Pair(grid[0][0], 0, 0));

       int[] delRow = {-1, 0, 1, 0};
       int[] delCol = {0, 1, 0, -1};

       while(!pq.isEmpty()){
        Pair it = pq.peek();
        pq.remove();
        int time = it.time;
        int r = it.row;
        int c = it.col;
        if(time > dist[r][c]) continue;
        if(r==n-1 && c == n-1) return time;

        for(int i = 0; i < 4 ;i++){

            int nr = r + delRow[i];
            int nc = c + delCol[i];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n) {

            int newTime = Math.max(time, grid[nr][nc]);

            if(newTime < dist[nr][nc]) {
                dist[nr][nc] = newTime;
                pq.add(new Pair(newTime, nr, nc));
            }
        }

        }
        
    }
    return -1;
    }
}