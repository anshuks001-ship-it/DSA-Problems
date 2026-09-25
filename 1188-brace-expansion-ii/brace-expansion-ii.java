import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        Set<String> res = new TreeSet<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(expression);
        visited.add(expression);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int right = curr.indexOf('}');
            
            if (right == -1) {
                res.add(curr);
            } else {
                int left = curr.lastIndexOf('{', right);
                String before = curr.substring(0, left);
                String after = curr.substring(right + 1);
                String[] parts = curr.substring(left + 1, right).split(",");
                
                for (String part : parts) {
                    String next = before + part + after;
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        
        return new ArrayList<>(res);
    }
}