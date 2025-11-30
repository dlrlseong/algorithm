import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r;
	static int t[];
	static final int INF = Integer.MAX_VALUE;
	static List<listNode> adjList[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		t = new int[1 + n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}

		adjList = new ArrayList[1 + n];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new listNode(to, weight));
			adjList[to].add(new listNode(from, weight));
		}

		int max = -1;
		for (int i = 1; i <= n; i++) {
			// Dijkstra
			PriorityQueue<queueNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
			int shortestPath[] = new int[1 + n];
			Arrays.fill(shortestPath, INF);
			shortestPath[i] = 0;
			pq.offer(new queueNode(i, 0));
			while (!pq.isEmpty()) {
				queueNode cur = pq.poll();
				int curVtx = cur.v;
				int curWeight = cur.cost;

				if (curWeight > shortestPath[curVtx])
					continue;

				for (listNode next : adjList[curVtx]) {
					int nextVtx = next.to;
					int nextWeight = curWeight + next.weight;
					if (shortestPath[nextVtx] > nextWeight) {
						shortestPath[nextVtx] = nextWeight;
						pq.offer(new queueNode(nextVtx, nextWeight));
					}
				}
			}

			int items = 0;
			for (int j = 1; j <= n; j++) {
				if (shortestPath[j] <= m) {
					items += t[j];
				}
			}
			max = Math.max(max, items);
		}
		System.out.println(max);
	}

	static class queueNode {
		int v, cost;

		public queueNode(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

	}

	static class listNode {
		int to, weight;

		public listNode(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

	}
}
