import java.io.*;
import java.util.*;

public class Main {
	static int N, P;
	static int V[], parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		V = new int[1 + N];
		int minV = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			V[i] = Integer.parseInt(st.nextToken());
			minV = Math.min(minV, V[i]);
		}
		Edge edges[] = new Edge[P];
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, 2 * weight + V[from] + V[to]);
		}
		makeSet();
		Arrays.sort(edges);
		int cnt = 0;
		int sum = 0;
		for (Edge e : edges) {
			if (!union(e.from, e.to))
				continue;
			sum += e.weight;
			cnt++;
			if (cnt == N - 1)
				break;
		}

		System.out.println(sum + minV);

	}

	private static void makeSet() {
		parent = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parent[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
