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

		// 인접 리스트 생성
		List<Node>[] adjList = new List[1 + V];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
		}

		// 다익스트라 알고리즘
		int[] dist = new int[1 + V];
		Arrays.fill(dist, INF);
		dist[Start] = 0;

		// 우선순위 큐 (거리 기준 오름차순)
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		pq.offer(new Node(Start, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int currentVertex = current.to;
			int currentDistance = current.weight;

			// 이미 처리된 정점이면 스킵
			if (currentDistance > dist[currentVertex]) {
				continue;
			}

			// 인접 정점들 확인
			for (Node next : adjList[currentVertex]) {
				int nextVertex = next.to;
				int newDistance = currentDistance + next.weight;

				// 더 짧은 경로를 발견했으면 갱신
				if (newDistance < dist[nextVertex]) {
					dist[nextVertex] = newDistance;
					pq.offer(new Node(nextVertex, newDistance));
				}
			}
		}

		// 결과 출력
		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}