import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int INF = Integer.MAX_VALUE;
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        List<Edge>[] graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int mn = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        List<Integer> mcdonalds = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < mn; i++) {
            mcdonalds.add(Integer.parseInt(st.nextToken()));
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int sn = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        List<Integer> starbucks = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sn; i++) {
            starbucks.add(Integer.parseInt(st.nextToken()));
        }
        
        int[] distFromMc = dijkstra(graph, mcdonalds, V);
        int[] distFromSb = dijkstra(graph, starbucks, V);
        
        int result = INF;
        Set<Integer> stores = new HashSet<>();
        for (int mc : mcdonalds) stores.add(mc);
        for (int sb : starbucks) stores.add(sb);
        
        for (int i = 1; i <= V; i++) {
            if (stores.contains(i)) continue;
            
            if (distFromMc[i] <= x && distFromSb[i] <= y) {
                result = Math.min(result, distFromMc[i] + distFromSb[i]);
            }
        }
        
        System.out.println(result == INF ? -1 : result);
    }
    
    static int[] dijkstra(List<Edge>[] graph, List<Integer> sources, int V) {
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        for (int source : sources) {
            dist[source] = 0;
            pq.offer(new int[]{source, 0});
        }
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int nowDist = current[1];
            
            if (dist[now] < nowDist) continue;
            
            for (Edge edge : graph[now]) {
                int next = edge.to;
                int nextDist = nowDist + edge.weight;
                
                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.offer(new int[]{next, nextDist});
                }
            }
        }
        
        return dist;
    }
}