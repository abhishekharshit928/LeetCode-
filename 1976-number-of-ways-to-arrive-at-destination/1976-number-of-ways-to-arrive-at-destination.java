class Pair {
    int first;
    long second;

    Pair(int first, long second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int countPaths(int n, int[][] roads) {

        List<List<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int time = roads[i][2];

            adj.get(u).add(new Pair(v, time));
            adj.get(v).add(new Pair(u, time));
        }

        long dist[] = new long[n];
        int ways[] = new int[n];

        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.second, b.second)
        );

        pq.offer(new Pair(0, 0));

        int mod = (int)(1e9 + 7);

        while(!pq.isEmpty()) {

            Pair it = pq.poll();

            int node = it.first;
            long time = it.second;

            if(time > dist[node]) continue;

            for(Pair iter : adj.get(node)) {

                int adjNode = iter.first;
                long edw = iter.second;

                if(time + edw < dist[adjNode]) {

                    dist[adjNode] = time + edw;

                    pq.offer(new Pair(adjNode, time + edw));

                    ways[adjNode] = ways[node];
                }

                else if(time + edw == dist[adjNode]) {

                    ways[adjNode] =
                        (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n-1];
    }
}
