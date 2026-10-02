class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder currentString, int open, int close,int max) {
        if (currentString.length() == max * 2) {
            res.add(currentString.toString());
            return;
        }

        if (open < max) {
            currentString.append("(");
            backtrack(res, currentString, open + 1, close, max);
            currentString.deleteCharAt(currentString.length() - 1);
        }
        if (close < open) {
            currentString.append(")");
            backtrack(res, currentString, open, close + 1, max);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }
}