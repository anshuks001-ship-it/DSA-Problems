import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });
        
        int[] ans = new int[m];
        long currentTime = 0;
        
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }
            
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[]{server[1], server[2]});
                }
            }
            
            int[] chosenServer = freeServers.poll();
            ans[j] = chosenServer[1];
            busyServers.offer(new int[]{(int)(currentTime + tasks[j]), chosenServer[0], chosenServer[1]});
        }
        
        return ans;
    }
}