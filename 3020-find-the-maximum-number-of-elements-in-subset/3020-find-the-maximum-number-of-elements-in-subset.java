import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 0;

        // Special handling for 1
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            if (cnt % 2 == 0)
                cnt--;
            ans = Math.max(ans, cnt);
        }

        for (long x : freq.keySet()) {

            if (x == 1) continue;

            long cur = x;
            int len = 0;

            while (true) {

                Integer cnt = freq.get(cur);

                if (cnt == null)
                    break;

                if (cnt >= 2) {
                    len += 2;

                    if (cur > 1000000000L / cur)
                        break;

                    cur *= cur;
                } else {
                    len++;
                    break;
                }
            }

            if ((len & 1) == 0)
                len--;

            ans = Math.max(ans, len);
        }

        return ans;
    }
}