class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] count = new long[k];

        for (int num : nums) {
            long[] newCount = new long[k];
            int val = num % k;
            
            newCount[val]++;
            
            for (int r = 0; r < k; r++) {
                if (count[r] > 0) {
                    newCount[(r * val) % k] += count[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                count[r] = newCount[r];
                result[r] += count[r];
            }
        }

        return result;
    }
}