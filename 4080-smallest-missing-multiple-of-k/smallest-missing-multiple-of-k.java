class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] seen = new boolean[101];
        for (int num : nums) {
            seen[num] = true;
        }
        
        int current = k;
        while (current <= 100 && seen[current]) {
            current += k;
        }
        
        return current;
    }
}