class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        java.util.Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i <= n; i++) {
            if (dp[i] == Long.MAX_VALUE) continue;
            for (int jump = 1; jump <= 3; jump++) {
                int j = i + jump;
                if (j <= n) {
                    long cost = costs[j - 1] + (long) (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], dp[i] + cost);
                }
            }
        }
        
        return (int) dp[n];
    }
}