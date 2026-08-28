class Solution {
    int[] pool = new int[26];
    char[] half;
    char[] targetArr;
    int n;
    int m;
    String midStr = "";

    public String lexPalindromicPermutation(String s, String target) {
        n = s.length();
        m = n / 2;
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                midChar = (char) (i + 'a');
            }
            pool[i] = counts[i] / 2;
        }

        if (oddCount > 1) return "";

        if (oddCount == 1) {
            midStr = String.valueOf(midChar);
        }

        half = new char[m];
        targetArr = target.toCharArray();

        if (dfs(0, false)) {
            StringBuilder sb = new StringBuilder();
            sb.append(half);
            sb.append(midStr);
            for (int i = m - 1; i >= 0; i--) {
                sb.append(half[i]);
            }
            return sb.toString();
        }

        return "";
    }

    private boolean dfs(int idx, boolean isGreater) {
        if (idx == m) {
            if (isGreater) return true;
            
            int tIdx = m;
            if (!midStr.isEmpty()) {
                if (midStr.charAt(0) > targetArr[tIdx]) return true;
                if (midStr.charAt(0) < targetArr[tIdx]) return false;
                tIdx++;
            }
            for (int i = m - 1; i >= 0; i--) {
                if (half[i] > targetArr[tIdx]) return true;
                if (half[i] < targetArr[tIdx]) return false;
                tIdx++;
            }
            return false;
        }

        for (int i = 0; i < 26; i++) {
            if (pool[i] == 0) continue;
            char c = (char) (i + 'a');
            
            if (!isGreater && c < targetArr[idx]) continue;
            
            pool[i]--;
            half[idx] = c;
            
            if (dfs(idx + 1, isGreater || c > targetArr[idx])) {
                return true;
            }
            
            pool[i]++;
        }

        return false;
    }
}