import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w;
        Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    static List<Edge>[] graph;
    static boolean[] visited;
    static int farNode;
    static int maxDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        visited = new boolean[n + 1];
        maxDist = 0;
        farNode = 1;
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int dist) {
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farNode = node;
        }
        for (Edge e : graph[node]) {
            if (!visited[e.to]) {
                dfs(e.to, dist + e.w);
            }
        }
    }
}
