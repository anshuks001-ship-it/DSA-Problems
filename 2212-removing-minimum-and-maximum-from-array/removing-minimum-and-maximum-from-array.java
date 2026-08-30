class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        int deleteFront = j + 1;
        int deleteBack = n - i;
        int deleteBoth = (i + 1) + (n - j);

        return Math.min(deleteFront, Math.min(deleteBack, deleteBoth));
    }
}