class Solution {
    private boolean dfs(int node , boolean[] vis , boolean pathVis[] , List<List<Integer>> adj){
        vis[node] = true;
        pathVis[node] = true;
        for(int it : adj.get(node)){
            if(!vis[it]){
                if(dfs(it ,  vis , pathVis , adj)) return true;
            }
            else if(pathVis[it]) return true;
        }
        pathVis[node] = false;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int N = numCourses;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0 ; i < N ; i++){
            for(int []it: prerequisites){
                int u = it[0];
                int v = it[1];

                adj.get(v).add(u);
            }
        }

        boolean vis[] = new boolean[N];
        boolean pathVis[] = new boolean[N];
        for(int i = 0 ; i < N ; i++){
            if(!vis[i]){
                if(dfs(i , vis , pathVis , adj)) return false;
            }
        }
        return true;

         
    }
}