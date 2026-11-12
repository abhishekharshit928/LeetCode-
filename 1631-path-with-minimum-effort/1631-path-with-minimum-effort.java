class tuple{
    int f,s,t;
    tuple(int f , int s ,int t){
        this.f = f;
        this.s = s;
        this.t = t;
    }
}
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n =heights.length;
        int m = heights[0].length;
        int [][] dist = new int[n][m];

       for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        dist[i][j] = (int) 1e9;
    }
}

        int [] dr = {-1,0,1,0};
        int [] dc = {0,1,0,-1};
        PriorityQueue<tuple> pq = new PriorityQueue<>((a, b) -> a.f - b.f);
        pq.add(new tuple(0,0,0));
        dist[0][0] = 0;
        

        while(!pq.isEmpty()){
            tuple it = pq.peek();
            pq.remove();
            int diff = it.f;
            int r = it.s;
            int c = it.t;

            if(r == n-1 && c == m-1) return diff;

            for(int i =  0 ; i < 4 ; i++){
                int nr = r+dr[i];
                int nc = c+dc[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    int newEffort = 
                    Math.max(Math.abs(heights[r][c]-heights[nr][nc]),diff);

                    if(newEffort < dist[nr][nc]){
                        dist[nr][nc] = newEffort;
                        pq.add(new tuple(newEffort,nr,nc));
                    }
                }
            }
        }
        return 0;
        
    }
}