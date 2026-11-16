// class Solution {
//     public int findTheCity(int n, int[][] edges, int distanceThreshold) {
//         int[][] dist = new int[n][n];
//         int[] count = new int[n];

//         for(int i = 0; i < n; i++) {
//             for(int j = 0; j < n; j++) {
//                 dist[i][j] = (int)1e9;
//             }
//             dist[i][i] = 0;
//         }

//         for(int i = 0; i < edges.length; i++) {
//             int u = edges[i][0];
//             int v = edges[i][1];
//             int wt = edges[i][2];

//             dist[u][v] = wt;
//             dist[v][u] = wt;
//         }

//         for(int k = 0; k < n; k++) {
//             for(int i = 0; i < n; i++) {
//                 for(int j = 0; j < n; j++) {
//                     dist[i][j] = Math.min(
//                         dist[i][j],
//                         dist[i][k] + dist[k][j]
//                     );
//                 }
//             }
//         }

//         for(int i = 0; i < n; i++) {
//             int cnt = 0;

//             for(int j = 0; j < n; j++) {
//                 if(i != j && dist[i][j] <= distanceThreshold) {
//                     cnt++;
//                 }
//             }

//             count[i] = cnt;
//         }

//         int ans = -1;
//         int minCount = Integer.MAX_VALUE;

//         for(int i = 0; i < n; i++) {
//             if(count[i] <= minCount) {
//                 minCount = count[i];
//                 ans = i;
//             }
//         }
//         return ans;
//     }
// }
class Pair{
        int node;
        int d;
        Pair(int n, int dist){
            this.node = n;
            this.d = dist;
        }
    }
    
class Solution {
    int findTheCity(int n,int[][] edges,int distanceThreshold){
        
        List<List<Pair>> adj = new ArrayList<>();
        
        for(int i = 0 ; i < n ;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int edge[] : edges){
            adj.get(edge[0]).add(new Pair(edge[1] , edge[2]));
            adj.get(edge[1]).add(new Pair(edge[0] , edge[2]));
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> x.d-y.d);
        int city = 0;
        int cities = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < n; i++){
            
            
            int [] dist = new int[n];
            Arrays.fill(dist , Integer.MAX_VALUE);
            dist[i] = 0;
            
            pq.add(new Pair(i,0));
            
            while(!pq.isEmpty()){
                Pair it = pq.peek();
                pq.remove();
                
                int node = it.node;
                int wt = it.d;
                
                for(Pair iter : adj.get(node)){
                    int adjNode = iter.node;
                    int edw = iter.d;
                    
                    if(wt+edw <= distanceThreshold && wt+edw < dist[adjNode]){
                        dist[adjNode] = wt + edw;
                        pq.offer(new Pair(adjNode , wt+edw));
                    }
                }
            }
            
            int citiesReachable = 0;
            for(int j = 0; j<n; j++){
                if(i != j && dist[j] != Integer.MAX_VALUE){
                    citiesReachable++;
                }
            }
            if(citiesReachable <= cities){
                city = i;
                cities = citiesReachable;
            }
            
        }
        
        return city;
    }
}