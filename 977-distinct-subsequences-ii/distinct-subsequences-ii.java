class Solution {
    public int distinctSubseqII(String s) {
        long[] dp = new long[26];
        long mod = 1_000_000_007;
        
        for (char c : s.toCharArray()) {
            long sum = 0;
            for (long val : dp) {
                sum = (sum + val) % mod;
            }
            dp[c - 'a'] = (sum + 1) % mod;
        }
        
        long res = 0;
        for (long val : dp) {
            res = (res + val) % mod;
        }
        
        return (int) res;
    }
}