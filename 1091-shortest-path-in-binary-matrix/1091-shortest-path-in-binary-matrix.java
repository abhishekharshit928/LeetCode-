class Pair{
    int r,c;
    Pair(int r , int c){
        this.r =r;
        this.c=c;
    }
}
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[] delrow = {-1, -1, -1, 0, 0, 1, 1, 1};
         int[] delcol = {-1,  0,  1, -1, 1, -1, 0, 1};
        int n = grid.length;
        if(grid[0][0] == 1  || grid[n-1][n-1] == 1) return -1;
        int [][] copy = grid;
        int length = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        copy[0][0] = 1;
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int j= 0 ; j < size ; j++){
            Pair elem =q.poll();
            int row = elem.r;
            int col = elem.c;
            if(row == n-1 && col == n-1) return length;
            for(int i = 0 ; i < 8 ; i++){
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                if(nrow < n && nrow >= 0 && ncol < n && ncol >= 0 && copy[nrow][ncol] == 0){
                    q.add(new Pair(nrow , ncol));
                    copy[nrow][ncol] = 1;
                    
                }
            }
            }
            length++;

        }
        return -1; 
    }
}