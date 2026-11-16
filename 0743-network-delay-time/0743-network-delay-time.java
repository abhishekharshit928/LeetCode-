class Pair{
    int node;
    int wt;
    Pair(int n , int d){
        this.node = n;
        this.wt = d;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0 ; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        for(int [] t: times){
            adj.get(t[0]).add(new Pair(t[1],t[2]));
        }

        int []dist = new int[n+1];
        for(int i = 1 ; i <= n ; i++) dist[i] = (int)(1e9);

        dist[k] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> x.wt-y.wt);

        pq.add(new Pair(k,0));

        while(!pq.isEmpty()){
            Pair it  = pq.peek();
            pq.remove();

            int node = it.node;
            int wt = it.wt;

            for(Pair iter : adj.get(node))
            {
                int adjNode = iter.node;
                int edw = iter.wt;



                if(edw + wt < dist[adjNode]){
                    dist[adjNode] = edw + wt;
                    pq.offer(new Pair(adjNode , wt+edw));
                }
            }
        }

        for(int i = 1 ; i <= n; i++){
            if(dist[i] == (int)(1e9)) return -1;
        }
        int ans = Integer.MIN_VALUE;

        for(int i=1 ; i < dist.length ; i++){
            if(dist[i] > ans) ans = dist[i];
        }

        return ans;        
    }
}