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

    public void unionBySize(int x, int y) {
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
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        MyDSU ds = new MyDSU(n * n);

        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        int area = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(grid[i][j] == 1) {
                    int node = i * n + j;

                    for(int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if(nr >= 0 && nr < n &&
                           nc >= 0 && nc < n &&
                           grid[nr][nc] == 1) {

                            int adjNode = nr * n + nc;
                            ds.unionBySize(node, adjNode);
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(grid[i][j] == 0) {

                    Set<Integer> components = new HashSet<>();
                    int total = 1;

                    for(int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if(nr >= 0 && nr < n &&
                           nc >= 0 && nc < n &&
                           grid[nr][nc] == 1) {

                            int adjNode = nr * n + nc;
                            int root = ds.find(adjNode);

                            components.add(root);
                        }
                    }
                    for(int root : components) {
                        total += ds.size.get(root);
                    }

                    area = Math.max(area, total);
                }
                else {
                    int node = i * n + j;
                    area = Math.max(area, ds.size.get(ds.find(node)));
                }
            }
        }

        return area;
    }
}
