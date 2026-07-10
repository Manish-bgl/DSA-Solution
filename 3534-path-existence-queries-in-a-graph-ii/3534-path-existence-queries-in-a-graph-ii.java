import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int LOG = 18;

        // (value, original index)
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // original index -> position in sorted order
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        // far[i] = farthest position reachable in one jump
        int[] far = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && arr[r + 1][0] - arr[i][0] <= maxDiff) {
                r++;
            }
            far[i] = r;
        }

        // Binary lifting table
        int[][] up = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            up[0][i] = far[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int cur = u;
            int jumps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < v) {
                    jumps += 1 << k;
                    cur = up[k][cur];
                }
            }

            if (up[0][cur] < v)
                ans[i] = -1;
            else
                ans[i] = jumps + 1;
        }

        return ans;
    }
}