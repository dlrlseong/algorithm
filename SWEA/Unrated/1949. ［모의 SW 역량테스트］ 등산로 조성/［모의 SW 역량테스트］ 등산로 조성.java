import java.io.*;
import java.util.*;

public class Solution {
	static int N, K, MaxLength;
	static int Map[][];
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void dfs(int r, int c, int depth, boolean isDone) {

		if (isDone) { // 공사를 한경우
			// 내 위치에서 bfs해서 최대 깊이 + depth 반환
			Queue<int[]> q = new ArrayDeque<>();
			int maxDepth = -99;
			q.offer(new int[] { r, c, depth });
			while (!q.isEmpty()) {
				int cur[] = q.poll();
				maxDepth = cur[2];
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N)
						continue;
					if (Map[nr][nc] >= Map[cur[0]][cur[1]])
						continue;
					q.offer(new int[] { nr, nc, maxDepth + 1 });
				}
			}
			MaxLength = Math.max(MaxLength, maxDepth);
			return;
		}

		// 공사를 아직 안한경우
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				MaxLength = Math.max(MaxLength, depth);
				continue;
			}
			// 1. 공사 안할거다.
			if (Map[nr][nc] < Map[r][c]) {
				int tmp = Map[r][c];
				Map[r][c] = 999990;
				dfs(nr, nc, depth + 1, false);
				Map[r][c] = tmp;
			}

			// 2. 공사 할거다.
			for (int i = 1; i <= K; i++) {
				Map[nr][nc] -= i; // i 만큼 공사
				if (Map[nr][nc] < Map[r][c]) {
					int tmp = Map[r][c];
					Map[r][c] = 999990;
					dfs(nr, nc, depth + 1, true);
					Map[r][c] = tmp;
				}
				Map[nr][nc] += i;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Map = new int[N][N];
			int maxHeight = -1;
			MaxLength = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, Map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (Map[i][j] == maxHeight) {
						dfs(i, j, 1, false);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(MaxLength).append("\n");
		}
		System.out.println(sb);
	}

}
