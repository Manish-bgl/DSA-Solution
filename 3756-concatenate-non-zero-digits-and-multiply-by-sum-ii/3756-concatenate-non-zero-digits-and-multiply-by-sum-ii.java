// class Solution {
//     public int[] sumAndMultiply(String s, int[][] queries) {
        
//     }
import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digit = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos.add(i);
                digit.add(d);
            }
        }

        int m = digit.size();

        long[] prefSum = new long[m + 1];
        long[] prefNum = new long[m + 1];
        long[] pow10 = new long[m + 1];

        pow10[0] = 1;

        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 1; i <= m; i++) {
            prefSum[i] = prefSum[i - 1] + digit.get(i - 1);
            prefNum[i] = (prefNum[i - 1] * 10 + digit.get(i - 1)) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int l = queries[q][0];
            int r = queries[q][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[q] = 0;
                continue;
            }

            int len = right - left + 1;

            long sum = prefSum[right + 1] - prefSum[left];

            long x = prefNum[right + 1]
                    - (prefNum[left] * pow10[len]) % MOD;

            x %= MOD;
            if (x < 0)
                x += MOD;

            ans[q] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(ArrayList<Integer> list, int target) {

        int l = 0, r = list.size();

        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private int upperBound(ArrayList<Integer> list, int target) {

        int l = 0, r = list.size();

        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) > target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }
}