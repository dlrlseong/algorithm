import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, homeCnt, Map[][];
	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			Map = new int[N][N];
			homeCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if (Map[i][j] == 1)
						homeCnt++;
				}
			}
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, service(i, j));
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}

	private static int service(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> nq = new ArrayDeque<>();
		boolean[][] vis = new boolean[N][N];
		int cnt = 0, k = 1;
		int max = 0;
		vis[i][j] = true;
		q.offer(new int[] { i, j });
		while (cnt < homeCnt) {
			int cost = k * k + (k - 1) * (k - 1);
			while (!nq.isEmpty()) {
				q.offer(nq.poll());
			}
			while (!q.isEmpty()) {
				int cur[] = q.poll();
				cnt += Map[cur[0]][cur[1]];
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];

					if (!isInRange(nr, nc) || vis[nr][nc])
						continue;
					if (Math.abs(i - nr) + Math.abs(j - nc) > k - 1) {
						vis[nr][nc] = true;
						nq.offer(new int[] { nr, nc });
					} else {
						vis[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}
				}
			}
			if (cnt * M >= cost) {
				max = cnt;
			}
			k++;
		}
		return max;
	}

	private static boolean isInRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

}
