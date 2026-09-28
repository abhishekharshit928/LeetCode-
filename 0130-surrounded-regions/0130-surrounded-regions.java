class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(i==0 || i == n-1 || j == 0 || j == m-1){
                if(board[i][j] == 'O'){
                    q.add(new Pair(i , j));
                    vis[i][j] = true;
                  }
                }  
            }
        }
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};
         while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            q.remove();

            for(int i = 0 ; i < 4 ; i++){
                int nr = r + delrow[i];
                int nc = c + delcol[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && board[nr][nc] == 'O'){
                    q.add(new Pair(nr , nc));
                    vis[nr][nc] = true;
                }
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ;j++){
                if(board[i][j] == 'O' && !vis[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
}