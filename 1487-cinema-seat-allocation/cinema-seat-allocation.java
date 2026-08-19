import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMap = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            if (seat[1] >= 2 && seat[1] <= 9) {
                rowMap.put(seat[0], rowMap.getOrDefault(seat[0], 0) | (1 << (seat[1] - 2)));
            }
        }
        
        int maxFamilies = (n - rowMap.size()) * 2;
        
        for (int mask : rowMap.values()) {
            boolean left = (mask & 15) == 0;
            boolean right = (mask & 240) == 0;
            boolean middle = (mask & 60) == 0;
            
            if (left && right) {
                maxFamilies += 2;
            } else if (left || middle || right) {
                maxFamilies += 1;
            }
        }
        
        return maxFamilies;
    }
}