import java.util.*;

class Solution {
    private static final Map<Integer, Map<Integer, Integer>> factors = Map.of(
        0, Map.of(),
        1, Map.of(),
        2, Map.of(2, 1),
        3, Map.of(3, 1),
        4, Map.of(2, 2),
        5, Map.of(5, 1),
        6, Map.of(2, 1, 3, 1),
        7, Map.of(7, 1),
        8, Map.of(2, 3),
        9, Map.of(3, 2)
    );

    public String smallestNumber(String num, long t) {
        Map<Integer, Integer> need = getPrimeCount(t);

        if (need == null)
            return "-1";

        Map<Integer, Integer> factorCount = getFactorCount(need);

        if (sum(factorCount) > num.length())
            return construct(factorCount);

        Map<Integer, Integer> prefix = getPrimeCount(num);

        int firstZero = num.indexOf('0');

        if (firstZero == -1) {
            firstZero = num.length();
            if (isSubset(need, prefix))
                return num;
        }

        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';

            prefix = subtract(prefix, factors.get(d));

            int space = num.length() - 1 - i;

            if (i > firstZero)
                continue;

            for (int bigger = d + 1; bigger < 10; bigger++) {
                Map<Integer, Integer> remaining =
                    subtract(
                        subtract(need, prefix),
                        factors.get(bigger)
                    );

                Map<Integer, Integer> result =
                    getFactorCount(remaining);

                if (sum(result) <= space) {
                    int ones = space - sum(result);

                    return num.substring(0, i)
                        + bigger
                        + "1".repeat(ones)
                        + construct(result);
                }
            }
        }

        Map<Integer, Integer> result = getFactorCount(need);

        return "1".repeat(num.length() + 1 - sum(result))
            + construct(result);
    }

    private Map<Integer, Integer> getPrimeCount(long t) {
        Map<Integer, Integer> count = new HashMap<>();

        count.put(2, 0);
        count.put(3, 0);
        count.put(5, 0);
        count.put(7, 0);

        for (int p : new int[]{2, 3, 5, 7}) {
            while (t % p == 0) {
                t /= p;
                count.put(p, count.get(p) + 1);
            }
        }

        return t == 1 ? count : null;
    }

    private Map<Integer, Integer> getPrimeCount(String num) {
        Map<Integer, Integer> count = new HashMap<>();

        count.put(2, 0);
        count.put(3, 0);
        count.put(5, 0);
        count.put(7, 0);

        for (char c : num.toCharArray()) {
            Map<Integer, Integer> f = factors.get(c - '0');

            for (Map.Entry<Integer, Integer> e : f.entrySet()) {
                count.put(
                    e.getKey(),
                    count.get(e.getKey()) + e.getValue()
                );
            }
        }

        return count;
    }

    private Map<Integer, Integer> getFactorCount(
        Map<Integer, Integer> count) {

        int count8 = count.get(2) / 3;
        int remaining2 = count.get(2) % 3;

        int count9 = count.get(3) / 2;
        int count3 = count.get(3) % 2;

        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;

        int count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }

        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }

        Map<Integer, Integer> result = new HashMap<>();

        result.put(2, count2);
        result.put(3, count3);
        result.put(4, count4);
        result.put(5, count.get(5));
        result.put(6, count6);
        result.put(7, count.get(7));
        result.put(8, count8);
        result.put(9, count9);

        return result;
    }

    private String construct(Map<Integer, Integer> count) {
        StringBuilder sb = new StringBuilder();

        for (int d = 2; d <= 9; d++) {
            sb.append(String.valueOf(d).repeat(count.get(d)));
        }

        return sb.toString();
    }

    private boolean isSubset(
        Map<Integer, Integer> a,
        Map<Integer, Integer> b) {

        for (int p : a.keySet()) {
            if (b.get(p) < a.get(p))
                return false;
        }

        return true;
    }

    private Map<Integer, Integer> subtract(
        Map<Integer, Integer> a,
        Map<Integer, Integer> b) {

        Map<Integer, Integer> result = new HashMap<>(a);

        for (Map.Entry<Integer, Integer> e : b.entrySet()) {
            int key = e.getKey();
            int value = e.getValue();

            result.put(
                key,
                Math.max(0, result.get(key) - value)
            );
        }

        return result;
    }

    private int sum(Map<Integer, Integer> count) {
        int result = 0;

        for (int value : count.values())
            result += value;

        return result;
    }
}