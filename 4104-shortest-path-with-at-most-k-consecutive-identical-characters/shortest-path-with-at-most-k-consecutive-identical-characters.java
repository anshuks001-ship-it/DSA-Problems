import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        char startChar = labels.charAt(0);
        pq.offer(new long[]{0, 0, 1});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                char nextChar = labels.charAt(v);
                char currChar = labels.charAt(u);

                int nextCount;
                if (nextChar == currChar) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount <= k && d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new long[]{d + w, v, nextCount});
                }
            }
        }

        return -1;
    }
}