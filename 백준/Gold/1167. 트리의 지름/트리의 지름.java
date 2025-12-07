import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w;
        Edge(int t, int w) { this.to = t; this.w = w; }
    }

    static List<Edge>[] graph;
    static boolean[] visited;
    static int farNode, maxDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int w = Integer.parseInt(st.nextToken());
                graph[from].add(new Edge(to, w));
            }
        }

        visited = new boolean[V + 1];
        maxDist = 0;
        farNode = 1;
        dfs(1, 0);

        visited = new boolean[V + 1];
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
            if (!visited[e.to]) dfs(e.to, dist + e.w);
        }
    }
}
