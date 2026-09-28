class Pair {
    int first;
    int second;
    int dis;

    public Pair(int first, int second , int dis) {
        this.first = first;
        this.second = second;
        this.dis = dis;
    }
}
class Solution {
    public int[][] updateMatrix(int[][] mat) {

        
        int n = mat.length;
        int m = mat[0].length;
        int[][] ans = new int[n][m];
        
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Pair(i, j , 0));
                    ans[i][j] = 0;
                    vis[i][j] = true;
                }
            }
        }
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};
        while(!q.isEmpty()){
            int r = q.peek().first;
            int c = q.peek().second;
            int d = q.peek().dis;
            q.remove();
            int dis = d + 1;

            for(int i = 0 ; i < 4 ; i++){
                int nr = r+delrow[i];
                int nc = c + delcol[i];

                if(nr >= 0  && nr < n && nc >=0 && nc < m && !vis[nr][nc]){
                    q.add(new Pair(nr , nc , dis));
                    ans[nr][nc] = dis;
                    vis[nr][nc] = true;

                }
            }
        }

        return ans;
    }
}