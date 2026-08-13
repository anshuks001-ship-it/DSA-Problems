class Solution {
    class Node {
        char leftChar, rightChar;
        int length, prefix, suffix, best;

        Node(char c, int len, int pref, int suff, int b) {
            leftChar = c;
            rightChar = c;
            length = len;
            prefix = pref;
            suffix = suff;
            best = b;
        }

        Node(char lc, char rc, int len, int pref, int suff, int b) {
            leftChar = lc;
            rightChar = rc;
            length = len;
            prefix = pref;
            suffix = suff;
            best = b;
        }
    }

    Node[] tree;
    int n;

    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        int len = left.length + right.length;
        int pref = left.prefix;
        if (left.leftChar == right.leftChar && left.prefix == left.length) {
            pref = left.length + right.prefix;
        }

        int suff = right.suffix;
        if (left.rightChar == right.rightChar && right.suffix == right.length) {
            suff = right.length + left.suffix;
        }

        int b = Math.max(left.best, right.best);
        if (left.rightChar == right.leftChar) {
            b = Math.max(b, left.suffix + right.prefix);
        }

        return new Node(left.leftChar, right.rightChar, len, pref, suff, b);
    }

    void build(int node, int start, int end, String s) {
        if (start == end) {
            tree[node] = new Node(s.charAt(start), 1, 1, 1, 1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, s);
        build(2 * node + 1, mid + 1, end, s);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c, 1, 1, 1, 1);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        tree = new Node[4 * n];
        build(1, 0, n - 1, s);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }
        return ans;
    }
}