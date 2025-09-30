import java.io.*;
import java.util.*;

public class Main {

	static final int dr[] = { -1, 0, 1, 0 };
	static final int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Node> list = new ArrayList<>();
		int Map[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if (Map[i][j] == 2) {
					list.add(new Node(i, j, Integer.MAX_VALUE));
				}
			}
		}

		int result[][] = new int[N][N];

		Queue<int[]> q = new ArrayDeque<>();
		for (Node n : list) {
			boolean vis[][] = new boolean[N][N];
			q.offer(new int[] { n.r, n.c, 0 });
			vis[n.r][n.c] = true;
			while (!q.isEmpty()) {
				int cur[] = q.poll();
				if (Map[cur[0]][cur[1]] == 3) {
					n.d = Math.min(n.d, cur[2]);
				}
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (Map[nr][nc] == 1 || vis[nr][nc])
						continue;
					vis[nr][nc] = true;
					q.offer(new int[] { nr, nc, cur[2] + 1 });
				}
			}

			if (n.d == Integer.MAX_VALUE)
				n.d = -1;
			result[n.r][n.c] = n.d;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
