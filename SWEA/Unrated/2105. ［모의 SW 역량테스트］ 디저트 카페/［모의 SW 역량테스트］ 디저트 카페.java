import java.io.*;
import java.util.*;

public class Solution {
	static int N, cnt, start[], MAX;
	static int[][] Map;
	static final int[] dr = { 1, 1, -1, -1 };
	static final int[] dc = { 1, -1, -1, 1 };
	static boolean isDone[], vis[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			Map = new int[N][N];

			for (int i = 0; i < N; i++) { // @입력
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MAX = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == 0 && (j == 0 || j == N - 1))
						continue;
					if (i == N - 1 && (j == 0 || j == N - 1))
						continue;
					start = new int[] { i, j };
					isDone = new boolean[100 + 1];
					vis = new boolean[N][N];
					cnt = 0;
//					System.out.println();
//					System.out.println(i + " " + j + " " + Map[i][j] + " " + cnt);
					dfs(i, j, 0);

				}
			}
			sb.append("#").append(tc).append(" ").append(MAX).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int i, int j, int d) {
		if (i < 0 || j < 0 || i >= N || j >= N)
			return;
		if (i < start[0])
			return;
		if (i == start[0] && j == start[1] && d == 3) {
			MAX = Math.max(MAX, cnt);
			return;
		}
		if (isDone[Map[i][j]] || vis[i][j])
			return;
		isDone[Map[i][j]] = true;
		vis[i][j] = true;
		cnt++;

//		System.out.println(i + " " + j + " " + Map[i][j] + " " + cnt);
		dfs(i + dr[d], j + dc[d], d);
		dfs(i + dr[(d + 1) % 4], j + dc[(d + 1) % 4], (d + 1) % 4);
		isDone[Map[i][j]] = false;
		vis[i][j] = false;
		cnt--;
	}
}
