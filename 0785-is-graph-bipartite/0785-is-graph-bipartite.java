class Solution {
    
    private boolean dfs(int node, int[] color, int[][] graph) {

    for (int neighbour : graph[node]) {

        if (color[neighbour] == 0) {
            color[neighbour] = 3 - color[node];
            if (!dfs(neighbour, color, graph))
                return false;
        }

        else if (color[neighbour] == color[node]) {
            return false;
        }
    }

    return true;
}
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        int[] color = new int[V];
        for(int i = 0 ; i < graph.length ; i++){
            if(color[i] == 0){
                if(dfs(i , color , graph) == false) return false;
            }
        }
        return true;   
    }
}