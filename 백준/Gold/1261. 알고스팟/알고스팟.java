import java.io.*;
import java.util.*;

/*
 *  
 */

public class Main {
	static int N, M, Map[][], Result;
	static boolean vis[][], GameOver;
	static Deque<int[]> nq = new ArrayDeque<>();
	static final int dr[] = { 1, 0, -1, 0 }; // 하 우 상 좌
	static final int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		vis = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				Map[i][j] = arr[j] - '0';
			}
		}

		int i = 0;
		while (!GameOver) {
			bfs(i++);
		}
		System.out.println(Result);
	}

	private static void bfs(int n) {
		while (!nq.isEmpty()) {
			int cur[] = nq.pollFirst();
			int r = cur[0];
			int c = cur[1];

			Map[r][c] = 0;
		}

		Deque<int[]> q = new ArrayDeque<>();
		vis = new boolean[N][M];
		vis[0][0] = true;
		q.offerLast(new int[] { 0, 0 });

		while (!q.isEmpty()) {
			int cur[] = q.pollFirst();
			int r = cur[0];
			int c = cur[1];

			if (r == N - 1 && c == M - 1) {
				GameOver = true;
				Result = n;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (vis[nr][nc])
					continue;

				if (Map[nr][nc] == 1) {
					nq.offerLast(new int[] { nr, nc });
					vis[nr][nc] = true;
					continue;
				}

				vis[nr][nc] = true;
				q.offerLast(new int[] { nr, nc });
			}
		}
	}

}
