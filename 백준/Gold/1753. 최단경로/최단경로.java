import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static int Start;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		Start = Integer.parseInt(st.nextToken());

		Node adjList[] = new Node[1 + V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}

		int dist[] = new int[1 + V];
		boolean isVisited[] = new boolean[1 + V];
		for (int i = 1; i <= V; i++) {
			dist[i] = INF;
		}
		dist[Start] = 0;

		for (int i = 0; i < V; i++) {
			int minWeight = INF;
			int minIdx = -1;
			for (int j = 1; j <= V; j++) {
				if (!isVisited[j] && minWeight > dist[j]) {
					minWeight = dist[j];
					minIdx = j;
				}
			}
			if (minIdx == -1)
				break;
			isVisited[minIdx] = true;
			for (Node to = adjList[minIdx]; to != null; to = to.next) {
				if (dist[to.to] > minWeight + to.weight) {
					dist[to.to] = minWeight + to.weight;
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static class Node {
		int to, weight;
		Node next;

		public Node(int to, int weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
}
