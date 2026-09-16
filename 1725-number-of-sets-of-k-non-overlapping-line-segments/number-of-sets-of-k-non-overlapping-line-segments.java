class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1000000007;
        int N = n + k - 1;
        int K = 2 * k;
        
        if (K > N) {
            return 0;
        }
        
        long[] fact = new long[N + 1];
        long[] inv = new long[N + 1];
        fact[0] = 1;
        inv[0] = 1;
        
        for (int i = 1; i <= N; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        
        inv[N] = power(fact[N], MOD - 2, MOD);
        for (int i = N - 1; i >= 1; i--) {
            inv[i] = (inv[i + 1] * (i + 1)) % MOD;
        }
        
        long ans = fact[N];
        ans = (ans * inv[K]) % MOD;
        ans = (ans * inv[N - K]) % MOD;
        
        return (int) ans;
    }
    
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp % 2) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}