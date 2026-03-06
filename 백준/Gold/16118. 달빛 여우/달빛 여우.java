import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Node> adjList[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[1 + N];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}

		// fox Dijkstra
		double[] foxMin = new double[1 + N];
		Arrays.fill(foxMin, Double.MAX_VALUE);
		foxMin[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(a.weight, b.weight));
		pq.add(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.weight > foxMin[cur.vertex])
				continue;

			for (Node n : adjList[cur.vertex]) {
				int nextVtx = n.vertex;
				double nextWeight = cur.weight + n.weight;
				if (foxMin[nextVtx] > nextWeight) {
					foxMin[nextVtx] = nextWeight;
					pq.add(new Node(nextVtx, nextWeight));
				}
			}

		}

		// wolf Dijkstra
		double[][] wolfMin = new double[1 + N][2];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(wolfMin[i], Double.MAX_VALUE);
		}
		wolfMin[1][0] = 0;

		pq = new PriorityQueue<>((a, b) -> Double.compare(a.weight, b.weight));
		pq.add(new Node(1, 0, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curState = cur.depth;

			if (cur.weight > wolfMin[cur.vertex][curState])
				continue;

			for (Node n : adjList[cur.vertex]) {
				int nextVtx = n.vertex;
				int nextState = 1 - curState;
				double nextWeight = cur.weight;

				if (curState == 1) {
					nextWeight += n.weight * 2;
				} else {
					nextWeight += n.weight / 2.0;
				}

				if (wolfMin[nextVtx][nextState] > nextWeight) {
					wolfMin[nextVtx][nextState] = nextWeight;
					pq.add(new Node(nextVtx, nextWeight, nextState));
				}
			}
		}

		int answer = 0;
		for (int i = 2; i <= N; i++) {
			double wolfBest = Math.min(wolfMin[i][0], wolfMin[i][1]);
			if (foxMin[i] < wolfBest) {
				answer++;
			}
//			System.out.println(foxMin[i] + " " + wolfBest);
		}
		System.out.println(answer);

	}

	static class Node {
		int vertex, depth;
		double weight;

		public Node(int vertex, double weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		public Node(int vertex, double weight, int depth) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", depth=" + depth + "]";
		}

	}
}
