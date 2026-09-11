class Solution {
    public int totalNumbers(int[] digits) {
        int[] counts = new int[10];
        for (int digit : digits) {
            counts[digit]++;
        }
        
        int uniqueCount = 0;
        for (int i = 100; i <= 998; i += 2) {
            int[] currentCounts = new int[10];
            int temp = i;
            while (temp > 0) {
                currentCounts[temp % 10]++;
                temp /= 10;
            }
            
            boolean canForm = true;
            for (int j = 0; j < 10; j++) {
                if (currentCounts[j] > counts[j]) {
                    canForm = false;
                    break;
                }
            }
            
            if (canForm) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}