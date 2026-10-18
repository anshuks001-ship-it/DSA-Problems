class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[][] arr = new Integer[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = getDigitSum(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (!a[1].equals(b[1])) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int swaps = 0;
        boolean[] visited = new boolean[n];
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIndex.put(arr[i][0], i);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] || valToIndex.get(nums[i]) == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = valToIndex.get(nums[curr]);
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}