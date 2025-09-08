import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, K;
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

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
			Queue<Cell> q = new ArrayDeque<>();
			PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>() {
				@Override
				public int compare(Cell o1, Cell o2) {
					if (o1.cnt == o2.cnt)
						return Integer.compare(o1.t, o2.t) * -1;
					return Integer.compare(o1.cnt, o2.cnt);
				}
			});

			boolean[][] isCell = new boolean[500 + N + 500][500 + M + 500];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp != 0) {
						isCell[i + 500][j + 500] = true;
						q.offer(new Cell(i + 500, j + 500, tmp, tmp));
					}
				}
			}
//			System.out.println("0 " + q.size() + " " + pq.size());
			for (int i = 1; i <= K; i++) {
//				System.out.println(i + " " + q.size() + " " + pq.size());
				int pqsize = pq.size();
				for (int j = 0; j < pqsize; j++) {
					Cell c = pq.poll();
//					System.out.println(c);
					if (c.cnt == 0) {
						for (int d = 0; d < 4; d++) {
							int nr = c.r + dr[d];
							int nc = c.c + dc[d];
							if (isCell[nr][nc])
								continue;
							isCell[nr][nc] = true;
							q.offer(new Cell(nr, nc, c.t, c.t + 1));
						}
						if (c.t != 1)
							pq.offer(new Cell(c.r, c.c, c.t, i + c.t - 1));

					}
					else if (c.cnt == i)
						continue;
					else {
						pq.offer(c);
					}
				}
				int qsize = q.size();
				for (int j = 0; j < qsize; j++) {
					Cell c = q.poll();
					if (c.cnt == 1) {
						pq.offer(new Cell(c.r, c.c, c.t, 0));
					} else {
						q.offer(new Cell(c.r, c.c, c.t, c.cnt - 1));
					}
				}
//				System.out.println(i + " " + q.size() + " " + pq.size());
			}
			sb.append("#").append(tc).append(" ").append(q.size() + pq.size()).append("\n");
		}
		System.out.println(sb);
	}

	static class Cell {
		int r, c, t, cnt;

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", t=" + t + ", cnt=" + cnt + "]";
		}

		public Cell(int r, int c, int t, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.cnt = cnt;
		}

	}
}
