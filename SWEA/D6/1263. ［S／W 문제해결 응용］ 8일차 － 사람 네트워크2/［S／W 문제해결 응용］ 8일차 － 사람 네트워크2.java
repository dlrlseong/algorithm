import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] adjMatrix = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int minCC = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				boolean vis[] = new boolean[N];
				Queue<int[]> q = new ArrayDeque<>();
				int CC = 0;
				vis[i] = true;
				q.offer(new int[] { i, 0 });
				while (!q.isEmpty()) {
					int cur[] = q.poll();
					CC += cur[1];
					if (CC > minCC)
						break;
					for (int j = 0; j < N; j++) {
						if (!vis[j] && adjMatrix[cur[0]][j] == 1) {
							vis[j] = true;
							q.offer(new int[] { j, cur[1] + 1 });
						}
					}
				}
				minCC = Math.min(minCC, CC);
			}
			sb.append("#").append(tc).append(" ").append(minCC).append("\n");
		}
		System.out.println(sb);
	}
}
