import java.io.*;
import java.util.*;

public class Main {
	static int N, M, C;
	static int[][] visited, MAP;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		MAP = new int[N][M];
		visited = new int[N][M];
		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j] == 1 && visited[i][j] == 0) {
					max = Math.max(max, bfs(i, j));
					C++;
				}
			}
		}
		System.out.println(C);
		System.out.println(max);
	}

	private static int bfs(int i, int j) {
		int cnt = 0;
		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] { i, j });
		visited[i][j] = 1;
		while (!q.isEmpty()) {
			int[] cur = q.pollLast();
			cnt++;
			for (int d = 0; d < 4; d++) {
				int r = cur[0] + dr[d];
				int c = cur[1] + dc[d];

				if (r < 0 || c < 0 || r >= N || c >= M || MAP[r][c] == 0 || visited[r][c] != 0)
					continue;
				visited[r][c]++;
				q.offerLast(new int[] { r, c });
			}
		}
		return cnt;
	}
}
