
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        if (src == dst) return 0;

        List<List<List<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int price = flights[i][2];

            adj.get(u).add(Arrays.asList(v, price));
        }

        int[][] dist = new int[k + 2][n];

        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dist[i], (int)1e9);
        }

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.get(2), b.get(2))
        );

        pq.add(Arrays.asList(0, src, 0));
        dist[0][src] = 0;

        while (!pq.isEmpty()) {
            List<Integer> path = pq.poll();

            int noOFStops = path.get(0);
            int stop = path.get(1);
            int dis = path.get(2);

            if (stop == dst) return dis;

            if (noOFStops == k + 1) continue;

            for (List<Integer> it : adj.get(stop)) {
                int adjNode = it.get(0);
                int edw = it.get(1);

                if (dis + edw < dist[noOFStops + 1][adjNode]) {
                    dist[noOFStops + 1][adjNode] = dis + edw;

                    pq.add(Arrays.asList(
                        noOFStops + 1,
                        adjNode,
                        dis + edw
                    ));
                }
            }
        }

        return -1;
    }
}
