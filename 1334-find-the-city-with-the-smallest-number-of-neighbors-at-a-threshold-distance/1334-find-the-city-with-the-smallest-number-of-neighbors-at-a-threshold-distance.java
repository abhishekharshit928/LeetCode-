class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        int[] count = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = (int)1e9;
            }
            dist[i][i] = 0;
        }

        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        for(int i = 0; i < n; i++) {
            int cnt = 0;

            for(int j = 0; j < n; j++) {
                if(i != j && dist[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }

            count[i] = cnt;
        }

        int ans = -1;
        int minCount = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            if(count[i] <= minCount) {
                minCount = count[i];
                ans = i;
            }
        }

        return ans;
    }
}