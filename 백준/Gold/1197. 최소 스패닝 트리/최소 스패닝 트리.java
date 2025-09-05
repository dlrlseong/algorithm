import java.io.*;
import java.util.*;

public class Main {
	static int V, E, P[];

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Edge list[] = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[i] = new Edge(from, to, weight);
		}

		Arrays.sort(list);
		int weightSum = 0;
		int edgeCnt = 0;
		makeSet();
		
		for (Edge edge : list) {
			if (!union(edge.from, edge.to))
				continue;

			weightSum += edge.weight;
			edgeCnt++;
			if (edgeCnt == V - 1)
				break;
		}

		System.out.println(weightSum);

	}

	static public void makeSet() {
		P = new int[1 + V];
		for (int i = 1; i <= V; i++) {
			P[i] = i;
		}
	}

	static public int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	static public boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		P[bRoot] = aRoot;
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
