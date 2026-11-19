class MyDSU {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public MyDSU(int n) {

        for(int i = 0 ; i <= n ; i++){
            size.add(1);
            rank.add(0);
            parent.add(i);
        }
    }
    public int find(int x) {
        if(x == parent.get(x)) return x;
        
        int ulp = find(parent.get(x));
        parent.set(x, ulp);
        return parent.get(x);
    }
 
    public void unionSetByRank(int x, int y) {
        int ulp_u = find(x);
        int ulp_v = find(y);
        
        if(ulp_u == ulp_v) return;
        
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u , ulp_v);
        }
        else if(rank.get(ulp_v) < rank.get(ulp_u)){
            parent.set(ulp_v , ulp_u);
        }
        
        else {
                parent.set(ulp_u, ulp_v);
                rank.set(ulp_v, rank.get(ulp_v) + 1);
            }
            
       
    }

    public void unionSetBySize(int x, int y) {
        int ulp_u = find(x);
        int ulp_v = find(y);

        if(ulp_u == ulp_v) return;

        if(size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        }
        else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
};
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