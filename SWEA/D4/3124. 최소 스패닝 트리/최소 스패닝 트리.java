import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
	static int[] P;

	static class Edge implements Comparable<Edge> {
		int a, b, c;

		public Edge(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			List<Edge> list = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				list.add(new Edge(a, b, c));
			}

			Collections.sort(list);
			int cnt = 0;
			long weightSum = 0;
			makeSet();
			for (Edge e : list) {
				if (!union(e.a, e.b))
					continue;
				weightSum += e.c;
				cnt++;
				if (cnt == V - 1)
					break;
			}
			sb.append("#").append(tc).append(" ").append(weightSum).append("\n");
		}
		System.out.println(sb);
	}

	public static void makeSet() {
		P = new int[1 + V];
		for (int i = 1; i <= V; i++) {
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
		P[bRoot] = aRoot;
		return true;

	}

}
