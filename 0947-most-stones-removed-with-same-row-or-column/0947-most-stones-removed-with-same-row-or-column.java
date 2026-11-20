class MyDSU {
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public MyDSU(int n) {
        for(int i = 0; i <= n; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    public int find(int x) {
        if(x == parent.get(x)) return x;

        int ulp = find(parent.get(x));
        parent.set(x, ulp);

        return parent.get(x);
    }

    public void unionSet(int x, int y) {
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
}

class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        MyDSU ds = new MyDSU(n);

        for(int i = 0 ; i < n ; i++){
            for(int j = i+1 ; j < n; j++){
                int r1 = stones[i][0];
                int c1 =stones[i][1];

                int r2 = stones[j][0];
                int c2 = stones[j][1];

                if(r1 == r2 || c1 == c2){
                    ds.unionSet(i, j);
                }
            }
        }

        int count = 0;

        for(int i = 0; i < n; i++) {
           if(ds.find(i) == i) {
             count++;
            }
        }

        return n-count;

        
    }
}