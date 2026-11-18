class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1) return -1;
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int i =0 ; i < connections.length ;i++){
            int u = connections[i][0];
            int v = connections[i][1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean connect[] = new boolean[n];
        Arrays.fill(connect, false);

        int components = 0;

for(int i = 0; i < n; i++) {
    if(!connect[i]) {
        components++;

        q.offer(i);
        connect[i] = true;
        while(!q.isEmpty()){
            int computer = q.poll();

            connect[computer] = true;

            for(int it : adj.get(computer)){

                if(connect[it] == false){
                    q.offer(it);
                    connect[it] = true;
                }

            }

        }

       
    }
}
        
        return components-1;
    }
}