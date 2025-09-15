import java.io.*;
import java.util.*;

public class Main {
	static int V, E, start, target, dist[];
	static List<Edge> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[1 + V];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
		}
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		dijkstra();
		System.out.println(dist[target]);
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		dist = new int[1 + V];
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.to] < cur.weight)
				continue;

			for (Edge next : graph[cur.to]) {
				if (dist[next.to] > next.weight + cur.weight) {
					dist[next.to] = next.weight + cur.weight;
					pq.offer(new Edge(next.to, next.weight + cur.weight));
				}
			}
		}
	}
	static class Edge implements Comparable<Edge> {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
