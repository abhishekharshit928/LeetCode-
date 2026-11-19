class MyDSU {
    int parent[];
    int rank[];

    public MyDSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int u) {
        if(u == parent[u]) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    public void unionSetBySize(int u, int v) {
        int pv = find(v);
        int pu = find(u);

        if(rank[pv] > rank[pu]) {
            parent[pu] = pv;
        }
        else if(rank[pu] > rank[pv]) {
            parent[pv] = pu;
        }
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();

        MyDSU dsu = new MyDSU(n);
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {

                String email = accounts.get(i).get(j);

                if(!map.containsKey(email)) {
                    map.put(email, i);
                }
                else {
                    int prevIdx = map.get(email);

                    dsu.unionSetBySize(i, prevIdx);
                }
            }
        }

        List<List<String>> mergedMail = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }

        for(String email : map.keySet()) {

            int index = dsu.find(map.get(email));

            mergedMail.get(index).add(email);
        }

        List<List<String>> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {

            if(mergedMail.get(i).size() == 0) {
                continue;
            }

            Collections.sort(mergedMail.get(i));

            List<String> temp = new ArrayList<>();

            String name = accounts.get(i).get(0);

            temp.add(name);
            temp.addAll(mergedMail.get(i));

            ans.add(temp);
        }

        return ans;
    }
}