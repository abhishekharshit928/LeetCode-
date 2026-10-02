class Solution {
    // private boolean dfs(int node , boolean[] vis , boolean pathVis[] , List<List<Integer>> adj){
    //     vis[node] = true;
    //     pathVis[node] = true;
    //     for(int it : adj.get(node)){
    //         if(!vis[it]){
    //             if(dfs(it ,  vis , pathVis , adj)) return true;
    //         }
    //         else if(pathVis[it]) return true;
    //     }
    //     pathVis[node] = false;
    //     return false;
    // }
    // public boolean canFinish(int numCourses, int[][] prerequisites) {
    //     int N = numCourses;

    //     List<List<Integer>> adj = new ArrayList<>();
    //     for(int i = 0 ; i < N ; i++){
    //         adj.add(new ArrayList<>());
    //     }

    //     for(int i = 0 ; i < N ; i++){
    //         for(int []it: prerequisites){
    //             int u = it[0];
    //             int v = it[1];

    //             adj.get(v).add(u);
    //         }
    //     }

    //     boolean vis[] = new boolean[N];
    //     boolean pathVis[] = new boolean[N];
    //     for(int i = 0 ; i < N ; i++){
    //         if(!vis[i]){
    //             if(dfs(i , vis , pathVis , adj)) return false;
    //         }
    //     }
    //     return true;

         
    // }


     public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for(int[] prerequisite: prerequisites){
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            graph.get(prerequisiteCourse).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int completed = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            completed++;

            for(int next: graph.get(course)){
                indegree[next]--;

                if(indegree[next]==0){
                    queue.offer(next);
                }
            }
        }
        return completed == numCourses;
    }
}