import java.io.*;
import java.util.*;

public class Solution {

	static int V, x[], y[], P[];
	static Edge edgelist[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			x = new int[V];
			y = new int[V];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < V; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < V; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			double tax = Double.parseDouble(br.readLine());

			edgelist = new Edge[(V * (V - 1)) / 2];
			int idx = 0;

			for (int i = 0; i < V; i++) {
				for (int j = i + 1; j < V; j++) {
					edgelist[idx++] = new Edge(i, j);
				}
			}
			Arrays.sort(edgelist);

			make();
			int e_num = 0;
			Double SUM = 0.0;
			for (Edge e : edgelist) {
				int from = e.from;
				int to = e.to;

				if (!union(from, to))
					continue;
				SUM += e.weight * tax;
				e_num++;
				if (e_num == V - 1)
					break;

			}

			sb.append("#").append(tc).append(" ").append(Math.round(SUM)).append("\n");
		}
		System.out.println(sb);
	}

	public static void make() {
		P = new int[V];
		for (int i = 0; i < V; i++) {
			P[i] = i;
		}
	}

	public static int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		if (aRoot > bRoot)
			P[aRoot] = bRoot;
		else
			P[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
			this.weight = distance(from, to);
		}

		public long distance(int from, int to) {
			long diff_x = x[from] - x[to];
			long diff_y = y[from] - y[to];
			return (diff_x * diff_x) + (diff_y * diff_y);
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
}
