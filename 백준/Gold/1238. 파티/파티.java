import java.io.*;
import java.util.*;

public class Main {

	static int N, M, X;
	static List<Node> adjList[];
	static final int INF = Integer.MAX_VALUE;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

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
		}

		// x 다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));

		int xShortestPath[] = new int[1 + N];
		Arrays.fill(xShortestPath, INF);
		xShortestPath[X] = 0;
		pq.offer(new Node(X, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curVtx = cur.v;
			int curWeight = cur.weight;

			if (curWeight > xShortestPath[curVtx])
				continue;

			for (Node next : adjList[curVtx]) {
				int nextVtx = next.v;
				int nextWeight = curWeight + next.weight;
				if (xShortestPath[nextVtx] > nextWeight) {
					xShortestPath[nextVtx] = nextWeight;
					pq.offer(new Node(nextVtx, nextWeight));
				}
			}
		}

		// 나머지 노드 다익스트라
		int nShortestPath[] = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			int ShortestPath[] = new int[1 + N];
			Arrays.fill(ShortestPath, INF);
			ShortestPath[i] = 0;
			pq.offer(new Node(i, 0));
			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				int curVtx = cur.v;
				int curWeight = cur.weight;

				if (curWeight > ShortestPath[curVtx])
					continue;

				for (Node next : adjList[curVtx]) {
					int nextVtx = next.v;
					int nextWeight = curWeight + next.weight;
					if (ShortestPath[nextVtx] > nextWeight) {
						ShortestPath[nextVtx] = nextWeight;
						pq.offer(new Node(nextVtx, nextWeight));
					}
				}
			}
			nShortestPath[i] = ShortestPath[X];
		}

		int max = -1;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, nShortestPath[i] + xShortestPath[i]);
		}
		System.out.println(max);

	}

	static class Node {
		int v, weight;

		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

	}
}
