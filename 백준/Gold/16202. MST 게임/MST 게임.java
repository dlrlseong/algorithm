import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		List<Edge> edgeList = new ArrayList<>();

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, i));
		}

		for (int turn = 0; turn < K; turn++) {
			makeSet();
			int cnt = 0;
			int cost = 0;
			boolean flag = false;
			for (int i = 0; i < edgeList.size(); i++) {
				Edge e = edgeList.get(i);
				if (union(e.from, e.to)) {
					cnt++;
					cost += e.weight;
					if (cnt == N - 1) {
						sb.append(cost).append(" ");
						flag = true;
						break;
					}
				}
			}
			if (!flag)
				sb.append(0).append(" ");
			edgeList.remove(0);
		}
		System.out.println(sb);
	}

	public static void makeSet() {
		P = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			P[i] = i;
		}
	}

	public static int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		if (rootA > rootB)
			P[rootB] = rootA;
		else
			P[rootA] = rootB;
		return true;
	}

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}

}
