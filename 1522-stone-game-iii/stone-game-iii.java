class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[3];
        
        for (int i = n - 1; i >= 0; i--) {
            int takeOne = stoneValue[i] - dp[(i + 1) % 3];
            int takeTwo = i + 1 < n ? stoneValue[i] + stoneValue[i + 1] - dp[(i + 2) % 3] : Integer.MIN_VALUE;
            int takeThree = i + 2 < n ? stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[(i + 3) % 3] : Integer.MIN_VALUE;
            
            dp[i % 3] = Math.max(takeOne, Math.max(takeTwo, takeThree));
        }
        
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}