class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }

        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0, right = 0;
        int logsLen = logs.length;

        for (int i = 0; i < m; i++) {
            int qVal = indexedQueries[i][0];
            int qIndex = indexedQueries[i][1];
            int windowStart = qVal - x;
            int windowEnd = qVal;

            while (right < logsLen && logs[right][1] <= windowEnd) {
                countMap.put(logs[right][0], countMap.getOrDefault(logs[right][0], 0) + 1);
                right++;
            }

            while (left < logsLen && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                countMap.put(serverId, countMap.get(serverId) - 1);
                if (countMap.get(serverId) == 0) {
                    countMap.remove(serverId);
                }
                left++;
            }

            ans[qIndex] = n - countMap.size();
        }

        return ans;
    }
}