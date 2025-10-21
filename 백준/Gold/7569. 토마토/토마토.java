import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H, cnt;
	static int arr[][][];
	static final int dr[] = { 0, 0, -1, 0, 1, 0 };
	static final int dc[] = { 0, 0, 0, 1, 0, -1 };
	static final int dh[] = { 1, -1, 0, 0, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		arr = new int[H][N][M];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if (arr[i][j][k] == 1 || arr[i][j][k] == 0) {
						cnt++;
						if (arr[i][j][k] == 1) {
							q.offer(new int[] { i, j, k });
						}
					}
				}
			}
		}
		bfs(q);
	}

	private static void bfs(Queue<int[]> q) {
		int day = -1;
		int total = 0;
		while (!q.isEmpty()) {
			int qs = q.size();
			for (int i = 0; i < qs; i++) {
				int[] cur = q.poll();
				total++;
				for (int d = 0; d < 6; d++) {
					int nh = cur[0] + dh[d];
					int nr = cur[1] + dr[d];
					int nc = cur[2] + dc[d];
					if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (arr[nh][nr][nc] == 0) {
						arr[nh][nr][nc] = 1;
						q.offer(new int[] { nh, nr, nc });
					}
				}
			}
			day++;
		}

		if (total == cnt)
			System.out.println(day);
		else
			System.out.println(-1);
	}
}
