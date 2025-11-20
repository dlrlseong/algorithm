import java.io.*;
import java.util.*;

public class Main {

	static int N, P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		for (int from = 1; from <= N - 1; from++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int to = 1 + from; to <= N; to++) {
				int weight = Integer.parseInt(st.nextToken());
				pq.offer(new Edge(from, to, weight));
			}
		}

		@SuppressWarnings("unchecked")
		List<Integer> adjList[] = new ArrayList[1 + N];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		makeSet();

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (union(e.from, e.to)) {
				adjList[e.from].add(e.to);
				adjList[e.to].add(e.from);
//				System.out.println(e);
//				System.out.println(Arrays.toString(P));
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(adjList[i].size()).append(" ");
			Collections.sort(adjList[i]);
			for (Integer n : adjList[i]) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
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

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

}
