import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
        
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);
        
        boolean isIsolated = true;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                isIsolated = false;
                break;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isIsolated || !isSuspicious[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private void dfs(int curr, List<List<Integer>> graph, boolean[] isSuspicious) {
        isSuspicious[curr] = true;
        for (int neighbor : graph.get(curr)) {
            if (!isSuspicious[neighbor]) {
                dfs(neighbor, graph, isSuspicious);
            }
        }
    }
}