class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        ans[0] = ".".repeat(n);

        for (int i = 1; i < m; i++) {
            ans[i] = "#".repeat(n - 1) + ".";
        }

        return ans;
    }
}
