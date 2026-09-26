class Solution {
    private void traversal(int i , int j , boolean[][] vis , char[][] grid ){
        vis[i][j] = true;
        if(i >= 0 && i-1 >= 0 && vis[i-1][j] == false && grid[i-1][j] == '1' ) { // up
            traversal(i-1 , j , vis , grid);
        }
        if(i < grid.length  && i+1 < grid.length && vis[i+1][j] == false && grid[i+1][j] == '1') { // down
            traversal(i+1 , j , vis , grid);
        }
        if(j > 0 && j-1 >= 0 && vis[i][j-1] == false && grid[i][j-1] == '1') { // left
            traversal(i , j-1 , vis , grid);
        }
        if( j< grid[0].length && j+1 < grid[0].length && vis[i][j+1] == false && grid[i][j+1] == '1') { // right
            traversal(i , j+1 , vis , grid);
        }

    }
    public int numIslands(char[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == '1' && vis[i][j] == false){
                    traversal(i , j , vis , grid);
                    ans++;
                }
            } 
        }
        return ans;
        
    }
}