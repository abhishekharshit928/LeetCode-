
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        HashSet<Integer> ans = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int indegree[] = new int[graph.length];

        List<List<Integer>> adjRev = new ArrayList<>();

for (int i = 0; i < graph.length; i++) {
    adjRev.add(new ArrayList<>());
}

for (int i = 0; i < graph.length; i++) {
    for (int it : graph[i]) {
        adjRev.get(it).add(i);
        indegree[i]++;
    }
}

        for (int i = 0; i < graph.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                ans.add(i);
            }
        }

        while (!q.isEmpty()) {
    int safeElem = q.poll();

    for (int node : adjRev.get(safeElem)) {
        indegree[node]--;

        if (indegree[node] == 0) {
            ans.add(node);
            q.offer(node);
        }
    }
}

        List<Integer> list = new ArrayList<>(ans);
        Collections.sort(list);

        return list;
    }
}
