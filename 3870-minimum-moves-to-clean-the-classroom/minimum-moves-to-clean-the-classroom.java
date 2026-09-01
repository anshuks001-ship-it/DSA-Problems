class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        int sx = -1, sy = -1;
        int lCount = 0;
        int[][] litterMap = new int[m][n];

        for (int i = 0; i < m; i++) {
            grid[i] = classroom[i].toCharArray();
            java.util.Arrays.fill(litterMap[i], -1);
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'L') {
                    litterMap[i][j] = lCount++;
                }
            }
        }

        if (lCount == 0) return 0;

        int[][][] bestEnergy = new int[m][n][1 << lCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                java.util.Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        java.util.Queue<int[]> q = new java.util.LinkedList<>();
        q.offer(new int[]{sx, sy, 0, energy, 0});
        bestEnergy[sx][sy][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int fullMask = (1 << lCount) - 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], mask = curr[2];
            int e = curr[3], steps = curr[4];

            if (mask == fullMask) {
                return steps;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] != 'X') {
                    int ne = e - 1;
                    if (ne < 0) continue;

                    if (grid[nr][nc] == 'R') {
                        ne = energy;
                    }

                    int nmask = mask;
                    if (grid[nr][nc] == 'L') {
                        nmask |= (1 << litterMap[nr][nc]);
                    }

                    if (ne > bestEnergy[nr][nc][nmask]) {
                        bestEnergy[nr][nc][nmask] = ne;
                        q.offer(new int[]{nr, nc, nmask, ne, steps + 1});
                    }
                }
            }
        }

        return -1;
    }
}