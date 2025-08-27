import java.io.*;
import java.util.*;

// idea : 최대신장트리를 만들고 마지막 엣지의 가중치를 출력

public class Main {
	static int V, E, P[];
	static int c, v;
	static edge[] edgelist;

	static class edge implements Comparable<edge> {
		int from, to, weight;

		public edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(edge o) {
			return Integer.compare(this.weight, o.weight) * -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		c = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		edgelist = new edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgelist[i] = new edge(from, to, weight);
		}

		Arrays.sort(edgelist);
//		for (edge e : edgelist) {
//			System.out.println(e.weight);
//		}
		int lastOne = 0;
		make();

		for (edge e : edgelist) {
			int from = e.from;
			int to = e.to;
			int weight = e.weight;

			if (!union(from, to))
				continue;
			if (find(c) == find(v)) {
				lastOne = weight;
				break;
			}
			lastOne++;
			if (lastOne == V - 1) {
				lastOne = weight;
				break;
			}
		}
		
		System.out.println(lastOne);

	}

	static public void make() {
		P = new int[V];
		for (int i = 0; i < V; i++) {
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

}
