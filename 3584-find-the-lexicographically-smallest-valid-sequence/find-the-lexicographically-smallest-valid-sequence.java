class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suf = new int[n + 1];
        suf[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            int next = suf[i + 1];
            if (next > 0 && word1.charAt(i) == word2.charAt(next - 1)) {
                suf[i] = next - 1;
            } else {
                suf[i] = next;
            }
        }
        int[] ans = new int[m];
        int j = 0;
        boolean changed = false;
        for (int i = 0; i < n; i++) {
            if (j < m && word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (!changed && j < m && suf[i + 1] <= j + 1) {
                ans[j++] = i;
                changed = true;
            }
            if (j == m) return ans;
        }
        return new int[0];
    }
}