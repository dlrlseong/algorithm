import java.io.*;
import java.util.*;

public class Main {

	static int V, E;
	static List<Integer> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[1 + V];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from); // 양방향 연결
		}

		boolean[] visited = new boolean[1 + V];
		visited[1] = true;
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		while (!q.isEmpty()) {
			int cur = q.poll();
//			System.out.println(cur);
			cnt++;
			for (Integer next : graph[cur]) {
				if (visited[next])
					continue;
				visited[next] = true;
				q.offer(next);
			}
		}
		System.out.println(cnt - 1);
	}
}