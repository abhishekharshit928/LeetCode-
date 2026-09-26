class Solution {
    private void traversal(int i , int j , boolean[][] vis , int[][] grid , int color , int originalColor ){
        vis[i][j] = true;
        grid[i][j] = color;

        if(i >= 0 && i-1 >= 0 && vis[i-1][j] == false && grid[i-1][j] == originalColor ) { // up
            traversal(i-1 , j , vis , grid , color,originalColor);
        }
        if(i < grid.length  && i+1 < grid.length && vis[i+1][j] == false && grid[i+1][j] == originalColor) { // down
            traversal(i+1 , j , vis , grid , color,originalColor);
        }
        if(j > 0 && j-1 >= 0 && vis[i][j-1] == false && grid[i][j-1] == originalColor) { // left
            traversal(i , j-1 , vis , grid , color,originalColor);
        }
        if( j< grid[0].length && j+1 < grid[0].length && vis[i][j+1] == false && grid[i][j+1] == originalColor) { // right
            traversal(i , j+1 , vis , grid , color,originalColor);
        }

    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        boolean[][] vis = new boolean[m][n];
        int originalColor = image[sr][sc];
        if (originalColor == color) return image;
        traversal(sr , sc , vis , image , color , originalColor);
        return image;
    }
}