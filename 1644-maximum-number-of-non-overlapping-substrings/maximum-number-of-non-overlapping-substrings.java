class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] l = new int[26];
        int[] r = new int[26];
        
        for (int i = 0; i < 26; i++) {
            l[i] = -1;
            r[i] = -1;
        }
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (l[c] == -1) l[c] = i;
            r[c] = i;
        }
        
        List<String> res = new ArrayList<>();
        int right = -1;
        
        for (int i = 0; i < n; i++) {
            if (i == l[s.charAt(i) - 'a']) {
                int newRight = check(s, i, l, r);
                if (newRight != -1) {
                    if (i > right) {
                        res.add(""); 
                    }
                    right = newRight;
                    res.set(res.size() - 1, s.substring(i, right + 1));
                }
            }
        }
        
        return res;
    }
    
    private int check(String s, int i, int[] l, int[] r) {
        int right = r[s.charAt(i) - 'a'];
        for (int j = i; j <= right; j++) {
            if (l[s.charAt(j) - 'a'] < i) {
                return -1;
            }
            right = Math.max(right, r[s.charAt(j) - 'a']);
        }
        return right;
    }
}