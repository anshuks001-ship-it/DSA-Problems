import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long totalK = (long) k1 + k2;
        long totalSum = 0;
        
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalSum += diff[i];
        }
        
        if (totalSum <= totalK) {
            return 0;
        }
        
        int[] count = new int[100001];
        int maxDiff = 0;
        for (int d : diff) {
            count[d]++;
            if (d > maxDiff) {
                maxDiff = d;
            }
        }
        
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (count[d] == 0) continue;
            long take = Math.min(totalK, (long) count[d]);
            count[d] -= take;
            count[d - 1] += (int) take;
            totalK -= take;
        }
        
        long result = 0;
        for (int d = 0; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * d * d;
            }
        }
        
        return result;
    }
}