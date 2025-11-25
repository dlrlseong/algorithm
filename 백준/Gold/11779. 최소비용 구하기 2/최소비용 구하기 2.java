import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	static List<Edge> adjList[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
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

			adjList[from].add(new Edge(to, weight));
		}

		st = new StringTokenizer(br.readLine(), " ");
		int Start = Integer.parseInt(st.nextToken());
		int End = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		int shortestPath[] = new int[1 + N];
		Arrays.fill(shortestPath, INF);
		shortestPath[Start] = 0;
		pq.offer(new Node(Start, 0, new ArrayList<>()));

		List<Integer> lastList = null;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curVtx = cur.vtx;
			int curWeight = cur.weight;
			List<Integer> list = cur.list;

			if (shortestPath[curVtx] < curWeight)
				continue;
			
			if (curVtx == End) {
				lastList = list;
			}

			for (Edge next : adjList[curVtx]) {
				int nextVtx = next.vtx;
				int nextWeight = curWeight + next.weight;
				if (shortestPath[nextVtx] > nextWeight) {
					shortestPath[nextVtx] = nextWeight;
					List<Integer> nextList = new ArrayList<>();
					for (Integer n : list) {
						nextList.add(n);
					}
					nextList.add(nextVtx);
					pq.offer(new Node(nextVtx, nextWeight, nextList));
				}
			}
		}
		sb.append(shortestPath[End]).append("\n");
		sb.append(lastList.size() + 1).append("\n");
		sb.append(Start).append(" ");
		for (Integer n : lastList) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}

	static class Node {
		int vtx, weight;
		List<Integer> list;

		public Node(int vtx, int weight, List<Integer> list) {
			super();
			this.vtx = vtx;
			this.weight = weight;
			this.list = list;
		}

	}

	static class Edge {
		int vtx, weight;

		public Edge(int vtx, int weight) {
			super();
			this.vtx = vtx;
			this.weight = weight;
		}

	}
}
