import java.io.*;
import java.util.*;

public class Main {
	static int N, P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		List<Edge> edgeList = new ArrayList<>();
		int weightSum = 0;

		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				int w = charToInt(c);
				weightSum += w;
				edgeList.add(new Edge(i, j, w));
			}
		}
		Collections.sort(edgeList, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});

		makeSet();
		int cnt = 0;
		int connectedSum = 0;
		for (Edge e : edgeList) {
			if (e.from == e.to || e.weight == 0) {
				continue;
			}
			if (!union(e.from, e.to)) {
				continue;
			}
//			System.out.println(e.from + " " + e.to + " " + e.weight);
			cnt++;
			connectedSum += e.weight;

		}
		if (cnt == N - 1) {
			System.out.println(weightSum - connectedSum);
		} else
			System.out.println(-1);
		return;

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

	public static int find(int a) {
		if (P[a] == a) {
			return a;
		}
		return P[a] = find(P[a]);
	}

	public static void makeSet() {
		P = new int[N];
		for (int i = 0; i < N; i++) {
			P[i] = i;
		}
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

	public static int charToInt(char c) {
		if ('a' <= c && c <= 'z') {
			return c - 'a' + 1;
		}
		if ('A' <= c && c <= 'Z') {
			return c - 'A' + 27;
		}
		return 0;
	}
}
