class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, long need1, long need2) {
        long commonCount = Math.min(need1, need2);
        long totalCost = commonCount * Math.min((long)costBoth, (long)cost1 + cost2);

        long rem1 = need1 - commonCount;
        totalCost += rem1 * Math.min((long)costBoth, (long)cost1);

        long rem2 = need2 - commonCount;
        totalCost += rem2 * Math.min((long)costBoth, (long)cost2);

        return totalCost;
    }
}