class Solution {
    static final long MOD = 1000000007L;
    long[] sum, sq, lazy;

    void update(int node, int start, int end, int l, int r) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            lazy[node]++;
            sq[node] = (sq[node] + 2 * sum[node] + (end - start + 1)) % MOD;
            sum[node] = (sum[node] + end - start + 1) % MOD;
            return;
        }

        push(node, start, end);

        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);
        lazy[node] = 0;
    }

    void apply(int node, int start, int end, long v) {
        long len = end - start + 1;
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 1, n, prev + 1, i + 1);
            ans = (ans + sq[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }
}