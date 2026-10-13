import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        for (int i = 0; i < n; i++) {
            squared[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squared);
        long sum = 0;
        int mid = (n + 1) / 2;
        for (int i = 0; i < mid; i++) {
            sum += squared[i + n - mid];
        }
        for (int i = 0; i < n - mid; i++) {
            sum -= squared[i];
        }
        return sum;
    }
}