import java.io.*;
import java.util.*;

public class Main {

	static int V, E, S;
	static boolean isVisited[];
	static List<Integer> graph[];
	static StringBuilder sb = new StringBuilder();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

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
		for (int i = 1; i <= V; i++) {
			Collections.sort(graph[i]);
		}

		isVisited = new boolean[1 + V];
		dfs(S);
		isVisited = new boolean[1 + V];
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append("\n");
		bfs(S);

		System.out.println(sb.deleteCharAt(sb.length() - 1));
	}

	private static void dfs(int depth) {
		isVisited[depth] = true;
		sb.append(depth).append(" ");
		for (Integer next : graph[depth]) {
			if (isVisited[next])
				continue;
			dfs(next);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		isVisited[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for (Integer next : graph[cur]) {
				if (isVisited[next])
					continue;
				isVisited[next] = true;
				q.offer(next);
			}
		}
	}

}