class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007;
        int sz = r - l + 1;

        if (n == 1) return sz;

        long[] up = new long[sz];
        long[] down = new long[sz];

        // Length = 2
        for (int v = 0; v < sz; v++) {
            up[v] = v;
            down[v] = sz - 1 - v;
        }

        if (n == 2) {
            long ans = 0;
            for (int v = 0; v < sz; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[sz];
            long[] newDown = new long[sz];

            long[] prefDown = new long[sz + 1];
            long[] sufUp = new long[sz + 1];

            for (int v = 0; v < sz; v++) {
                prefDown[v + 1] = (prefDown[v] + down[v]) % MOD;
            }

            for (int v = sz - 1; v >= 0; v--) {
                sufUp[v] = (sufUp[v + 1] + up[v]) % MOD;
            }

            for (int v = 0; v < sz; v++) {
                newUp[v] = prefDown[v];
                newDown[v] = sufUp[v + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 0; v < sz; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    
        
    }
}