import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, K;
	static int[][][] Map;
	static List<node> q;

	static final int[] dx = { 0, -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, 0, -1, 1 };

	static class node implements Comparable<node> {
		int x, y, w, d;

		public node(int x, int y, int w, int d) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
			this.d = d;
		}

		@Override
		public int compareTo(node o) {
			return Integer.compare(this.w, o.w) * -1;
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
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			Map = new int[N][N][1 + 1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				Map[x][y][0 + 0] = w;
				Map[x][y][0 + 1] = d;
			}

			q = new ArrayList<>();

			for (int c = 0; c < M; c++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (Map[i][j][0] == 0)
							continue;
						int x = i;
						int y = j;
						int w = Map[x][y][0];
						int d = Map[x][y][1];
						Map[x][y][0] = 0;
						Map[x][y][1] = 0;
						q.add(new node(x, y, w, d));
					}
				}
				Collections.sort(q);
				move();
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += Map[i][j][0];
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

	private static void move() {
		while (!q.isEmpty()) {
			node cur = q.remove(0);
			int x = cur.x;
			int y = cur.y;
			int w = cur.w;
			int d = cur.d;

			int nx = x + dx[d];
			int ny = y + dy[d];
			int nw = Map[nx][ny][0];
			int nd = Map[nx][ny][1];

			if (nw == 0) {
				Map[nx][ny][0] = w;
				Map[nx][ny][1] = d;
			} else {
				Map[nx][ny][0] += w;
				if (w > nw) {
					Map[nx][ny][1] = d;
				} else {
					Map[nx][ny][1] = nd;
				}
			}

			if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
				Map[nx][ny][0] /= 2;
				if (Map[nx][ny][1] == 1) {
					Map[nx][ny][1] = 2;
				} else if (Map[nx][ny][1] == 2) {
					Map[nx][ny][1] = 1;
				} else if (Map[nx][ny][1] == 3) {
					Map[nx][ny][1] = 4;
				} else
					Map[nx][ny][1] = 3;
			}

		}
	}

}
