import java.util.*;
import java.io.*;

class Main {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static int N, M, P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight) * -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(from, to, weight));
		}
		makeSet();
		st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int max = -1;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (union(e.from, e.to)) {
				max = e.weight;
				if (find(A) == find(B)) {
					break;
				}
			}
		}
		System.out.println(max);
	}

	public static void makeSet() {
		P = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			P[i] = i;
		}
	}

	public static int find(int a) {
		if (P[a] == a) {
			return a;
		}
		return P[a] = find(P[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) {
			return false;
		}
		if (rootA <= rootB)
			P[rootB] = rootA;
		else
			P[rootA] = rootB;
		return true;
	}
}