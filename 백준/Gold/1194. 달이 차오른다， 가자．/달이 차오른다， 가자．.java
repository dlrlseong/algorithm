import java.io.*;
import java.util.*;

public class Main {

	static final int dr[] = { -1, 0, 1, 0 };
	static final int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char map[][] = new char[r][c];
		boolean vis[][][] = new boolean[r][c][64];

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					Node tmp = new Node(i, j, 0, 0);
					pq.add(tmp);
					map[i][j] = '.';
				}
			}
		}
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int cr = cur.r;
			int cc = cur.c;
			int cw = cur.weight;
			int ck = cur.key;

			if (map[cr][cc] == '1') {
				System.out.println(cw);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int nk = ck;

				if (nr < 0 || nr >= r || nc < 0 || nc >= c)
					continue;
				if (map[nr][nc] == '#')
					continue;

				if ('a' <= (int) map[nr][nc] && (int) map[nr][nc] <= 'f') {
					nk = nk | 1 << map[nr][nc] - 'a';
				} else if ('A' <= (int) map[nr][nc] && (int) map[nr][nc] <= 'F') {
					if ((nk & 1 << map[nr][nc] - 'A') == 0)
						continue;
				}
				if (vis[nr][nc][nk])
					continue;
				vis[nr][nc][nk] = true;
				Node tmp = new Node(nr, nc, cw + 1, nk);
				pq.add(tmp);
			}

		}

		System.out.println(-1);

	}

	static class Node {
		int r, c, weight, key;

		public Node(int r, int c, int weight, int key) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", weight=" + weight + ", key=" + key + "]";
		}

	}
}
