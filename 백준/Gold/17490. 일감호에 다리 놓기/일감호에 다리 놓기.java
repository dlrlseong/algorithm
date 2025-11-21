import java.io.*;
import java.util.*;

public class Main {

	static int N, M, P[];
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		List<Edge> edgeList = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(0, i, weight));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.from == o2.from) {
					return Integer.compare(o1.to, o2.to);
				}
				return Integer.compare(o1.from, o2.from);
			}
		});

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from > to)
				pq.offer(new Edge(to, from, 0));
			else
				pq.offer(new Edge(from, to, 0));
		}

		int cnt = 0;

		for (int from = 1; from < N; from++) {
			int to = from + 1;
			if (!pq.isEmpty()) {
				Edge e = pq.peek();
				if (e.from == from && e.to == to) {
					pq.poll();
				} else {
					edgeList.add(new Edge(from, to, 0));
					cnt++;
				}
			} else {
				edgeList.add(new Edge(from, to, 0));
				cnt++;
			}
			if (from == 1) {
				to = N;
				if (!pq.isEmpty()) {
					Edge e = pq.peek();
					if (e.from == from && e.to == to) {
						pq.poll();
					} else {
						edgeList.add(new Edge(from, to, 0));
						cnt++;
					}
				} else {
					edgeList.add(new Edge(from, to, 0));
					cnt++;
				}
			}
		}

		makeSet();
		Collections.sort(edgeList, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});

		int unionCnt = 0;

		for (int i = 0; i < cnt; i++) {
			Edge e = edgeList.get(i);
			if (union(e.from, e.to)) {
				unionCnt++;
			}
		}
		if (unionCnt == N - 1) {
			System.out.println("YES");
			return;
		}
		long cost = 0;

		for (int i = cnt; i < edgeList.size(); i++) {
			Edge e = edgeList.get(i);
			if (union(e.from, e.to)) {
				unionCnt++;
				cost += e.weight;
				if (cost > K) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
	}

	public static void makeSet() {
		P = new int[1 + N];
		for (int i = 0; i <= N; i++) {
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

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
}
