import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V, map[][], P[];
	static boolean vis[][];
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };
	static List<int[]> list = new ArrayList<>();

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vis = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		V = 0;
		Deque<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!vis[i][j] && map[i][j] == 1) {
					V++;
					vis[i][j] = true;
					q.offerLast(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] cur = q.pollFirst();
						map[cur[0]][cur[1]] = V;
						for (int d = 0; d < 4; d++) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							if (nr < 0 || nc < 0 || nr >= N || nc >= M)
								continue;
							if (vis[nr][nc] || map[nr][nc] == 0)
								continue;
							vis[nr][nc] = true;
							q.offerLast(new int[] { nr, nc });
						}
					}

				}
			}
		}
//		for (int[] a : map)
//			System.out.println(Arrays.toString(a));

		for (int k = 1; k < V; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == k) {
						q.offerLast(new int[] { i, j });
					}
				}
			}

			bfs(q, k);
		}

		Edge[] eList = new Edge[list.size()];
		for (int i = 0; i < list.size(); i++) {
			eList[i] = new Edge(list.get(i)[0], list.get(i)[1], list.get(i)[2]);
		}

		make();
		Arrays.sort(eList);
		int e_num = 0;
		int cost = 0;
		for (Edge e : eList) {
			if (!union(e.from, e.to))
				continue;
			cost += e.weight;
			e_num++;
			if (e_num == V - 1)
				break;

		}

		if (e_num == V - 1) {
			System.out.println(cost);
		}

		else
			System.out.println("-1");

	}

	public static void make() {
		P = new int[1 + V];
		for (int i = 1; i <= V; i++) {
			P[i] = i;
		}
	}

	public static int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		P[bRoot] = aRoot;
		return true;
	}

	private static void bfs(Deque<int[]> q, int from) {
		int to[] = new int[1 + V];

		while (!q.isEmpty()) {
			int[] now = q.pollFirst();

			for (int d = 0; d < 4; d++) {
				int cnt = 0;
				int nr = now[0];
				int nc = now[1];
				while (true) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						break;
					if (map[nr][nc] == from)
						break;
					cnt++;
					if (map[nr][nc] == 0)
						continue;
					else {
						cnt -= 1;
						int tmp = map[nr][nc];
						if (cnt >= 2) {
							if (to[tmp] == 0)
								to[tmp] = cnt;
							else
								to[tmp] = Math.min(to[tmp], cnt);
						}
						break;
					}

				}
			}

		}
		// 출력문
//		System.out.println(from + " " + Arrays.toString(to));
		for (int i = from + 1; i <= V; i++) {
			if (to[i] != 0) {
				list.add(new int[] { from, i, to[i] });
			}
		}

	}

}
