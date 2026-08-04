class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = nums[0], max = nums[0];
        boolean[] present = new boolean[101];
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            present[num] = true;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!present[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}