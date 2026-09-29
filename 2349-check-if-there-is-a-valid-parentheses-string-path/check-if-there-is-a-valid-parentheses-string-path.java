class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        boolean[][][] visited = new boolean[m][n][m + n];
        return dfs(grid, 0, 0, 0, visited, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal, boolean[][][] visited, int m, int n) {
        bal += grid[r][c] == '(' ? 1 : -1;
        
        if (bal < 0 || bal > (m + n - 1) / 2 || visited[r][c][bal]) {
            return false;
        }
        
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }
        
        visited[r][c][bal] = true;
        
        if (r + 1 < m && dfs(grid, r + 1, c, bal, visited, m, n)) {
            return true;
        }
        if (c + 1 < n && dfs(grid, r, c + 1, bal, visited, m, n)) {
            return true;
        }
        
        return false;
    }
}