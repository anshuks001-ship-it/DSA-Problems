class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Queue<Integer>> groupToList = new HashMap<>();
        
        int currGroup = 0;
        numToGroup.put(sortedNums[0], 0);
        groupToList.put(0, new LinkedList<>());
        groupToList.get(0).add(sortedNums[0]);
        
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                currGroup++;
            }
            numToGroup.put(sortedNums[i], currGroup);
            groupToList.putIfAbsent(currGroup, new LinkedList<>());
            groupToList.get(currGroup).add(sortedNums[i]);
        }
        
        for (int i = 0; i < n; i++) {
            nums[i] = groupToList.get(numToGroup.get(nums[i])).poll();
        }
        
        return nums;
    }
}