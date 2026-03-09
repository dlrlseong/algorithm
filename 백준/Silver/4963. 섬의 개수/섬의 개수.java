import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				return;

			int[][] map = new int[h][w];
			boolean[][] vis = new boolean[h][w];

			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 0 || vis[i][j])
						continue;
					cnt++;
					vis[i][j] = true;
					q.add(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int n = cur[0];
						int c = cur[1];
						for (int d = 0; d < 8; d++) {
							int nr = n + dr[d];
							int nc = c + dc[d];

							if (nr < 0 || nr >= h || nc < 0 || nc >= w)
								continue;
							if (map[nr][nc] == 0 || vis[nr][nc])
								continue;

							vis[nr][nc] = true;
							q.add(new int[] { nr, nc });
						}
					}
				}
			}
			System.out.println(cnt);
		}

	}

}
