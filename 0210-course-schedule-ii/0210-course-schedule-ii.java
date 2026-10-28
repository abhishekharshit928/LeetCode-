class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] ans = new int[numCourses];

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
        int j = 0;

        while(!queue.isEmpty()){
            int course = queue.poll();
            ans[j++] = course;
            

            for(int next: graph.get(course)){
                indegree[next]--;

                if(indegree[next]==0){
                    queue.offer(next);
                }
            }
        }
        if(j == numCourses){
             return ans;
        }
        else return new int[0];
    }
}
