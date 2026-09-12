import java.util.Arrays;

class Solution {
    class Interval {
        int l, r, idx;
        long w;

        Interval(int l, int r, long w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervalsList.get(i);
            arr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }
        return solve(arr, n);
    }

    public int[] maximumWeight(int[][] intervals) {
        int n = intervals.length;
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(intervals[i][0], intervals[i][1], intervals[i][2], i);
        }
        return solve(arr, n);
    }

    private int[] solve(Interval[] arr, int n) {
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.idx, b.idx);
        });

        long[][] dpScore = new long[n + 1][5];
        int[][][] dpIds = new int[n + 1][5][];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dpScore[i], -1);
            dpScore[i][0] = 0;
            dpIds[i][0] = new int[0];
        }

        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1].l;
            long w = arr[i - 1].w;
            int origIdx = arr[i - 1].idx;

            int j = binarySearch(arr, l);

            for (int k = 1; k <= 4; k++) {
                long score1 = dpScore[i - 1][k];
                int[] ids1 = dpIds[i - 1][k];

                long score2 = -1;
                int[] ids2 = null;

                if (dpScore[j][k - 1] != -1) {
                    score2 = dpScore[j][k - 1] + w;
                    int[] prevIds = dpIds[j][k - 1];
                    ids2 = Arrays.copyOf(prevIds, prevIds.length + 1);
                    ids2[prevIds.length] = origIdx;
                    Arrays.sort(ids2);
                }

                if (score1 > score2) {
                    dpScore[i][k] = score1;
                    dpIds[i][k] = ids1;
                } else if (score2 > score1) {
                    dpScore[i][k] = score2;
                    dpIds[i][k] = ids2;
                } else {
                    if (score1 == -1) {
                        dpScore[i][k] = -1;
                        dpIds[i][k] = null;
                    } else {
                        if (isLexicographicallySmaller(ids1, ids2)) {
                            dpScore[i][k] = score1;
                            dpIds[i][k] = ids1;
                        } else {
                            dpScore[i][k] = score2;
                            dpIds[i][k] = ids2;
                        }
                    }
                }
            }
        }

        long maxScore = -1;
        int[] bestIds = null;

        for (int k = 1; k <= 4; k++) {
            if (dpScore[n][k] > maxScore) {
                maxScore = dpScore[n][k];
                bestIds = dpIds[n][k];
            } else if (dpScore[n][k] == maxScore && maxScore != -1) {
                if (isLexicographicallySmaller(dpIds[n][k], bestIds)) {
                    bestIds = dpIds[n][k];
                }
            }
        }

        return bestIds == null ? new int[0] : bestIds;
    }

    private int binarySearch(Interval[] arr, int targetL) {
        int left = 0, right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid].r < targetL) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans + 1;
    }

    private boolean isLexicographicallySmaller(int[] a, int[] b) {
        if (a == null) return false;
        if (b == null) return true;
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] < b[i]) return true;
            if (a[i] > b[i]) return false;
        }
        return a.length < b.length;
    }
}