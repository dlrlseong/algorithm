import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] list;
	static boolean visited[];
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			list[A].add(B);
			list[B].add(A);
		}

		visited = new boolean[N + 1];

		int answer = bfs(1);
		System.out.println(answer);
	}

	private static int bfs(int start) {

		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] { start, 0 });
		visited[start] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int[] cur = q.pollFirst();
			int node = cur[0];
			int distance = cur[1];
			if (distance >= 2)
				continue;

			for (int next : list[node]) {
				if (!visited[next]) {
					visited[next] = true;
					q.offerLast(new int[] { next, distance + 1 });
					cnt++;
				}
			}
		}

		return cnt;
	}
}
