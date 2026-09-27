class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        StringBuilder result = new StringBuilder();
        int direction = 1;
        
        for (int i = 0; i < n; i += direction) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                direction = -direction;
            } else {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }
}