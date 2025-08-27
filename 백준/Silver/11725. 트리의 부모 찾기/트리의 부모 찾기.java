import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());

		List<Integer>[] list = new ArrayList[1 + N];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		boolean[] visited = new boolean[1 + N];
		int[] parent = new int[1 + N];
		Deque<Integer> q = new ArrayDeque<>();
		q.offerLast(1);
		parent[1] = -1;
		visited[1] = true;

		while (!q.isEmpty()) {
			int cur = q.pollFirst();
			for (int next : list[cur]) {
				if (!visited[next]) {
					parent[next] = cur;
					visited[next] = true;
					q.offerLast(next);
				}
			}
		}
		for (int i = 2; i <= N; i++) {
//			System.out.println(parent[i]);
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb);
	}

}
