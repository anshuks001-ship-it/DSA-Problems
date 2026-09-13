import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        for (int i = 0; i < n * n; i++) {
            if (img1[i / n][i % n] == 1) {
                list1.add(i / n * 100 + i % n);
            }
            if (img2[i / n][i % n] == 1) {
                list2.add(i / n * 100 + i % n);
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        
        for (int i : list1) {
            for (int j : list2) {
                int key = i - j;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
        }
        
        return max;
    }
}