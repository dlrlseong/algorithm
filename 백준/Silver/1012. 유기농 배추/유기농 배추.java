import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int arr[][] = new int[N][M];
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				arr[r][c] = 1;
				q.add(new int[] { r, c });
			}

			int cnt = 0;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int curR = cur[0];
				int curC = cur[1];
				if (arr[curR][curC] == 0)
					continue;
				arr[curR][curC] = 0;
				cnt++;
				Queue<int[]> nq = new ArrayDeque<>();
				nq.add(cur);
				while (!nq.isEmpty()) {
					int[] tmp = nq.poll();
					int tmpR = tmp[0];
					int tmpC = tmp[1];
					for (int d = 0; d < 4; d++) {
						int nr = tmpR + dr[d];
						int nc = tmpC + dc[d];
						if (nr < 0 || nr >= N || nc < 0 || nc >= M)
							continue;
						if (arr[nr][nc] == 0)
							continue;
						arr[nr][nc] = 0;
						nq.offer(new int[] { nr, nc });
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}