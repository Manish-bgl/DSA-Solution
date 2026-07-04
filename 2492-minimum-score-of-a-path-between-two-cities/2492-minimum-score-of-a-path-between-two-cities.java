// class Solution {
//     public int minScore(int n, int[][] roads) {
        
//     }
// }
import java.util.*;

class Solution {

    static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int minScore(int n, int[][] roads) {

        List<Pair>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph[u].add(new Pair(v, d));
            graph[v].add(new Pair(u, d));
        }

        boolean[] visited = new boolean[n + 1];
        int[] ans = {Integer.MAX_VALUE};

        dfs(1, graph, visited, ans);

        return ans[0];
    }

    private void dfs(int node, List<Pair>[] graph, boolean[] visited, int[] ans) {

        visited[node] = true;

        for (Pair next : graph[node]) {

            ans[0] = Math.min(ans[0], next.dist);

            if (!visited[next.node]) {
                dfs(next.node, graph, visited, ans);
            }
        }
    }
}