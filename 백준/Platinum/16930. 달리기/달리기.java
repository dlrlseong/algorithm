import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static char[][] Map;
	static int minDepth[][], MIN, target[];
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };
	static Deque<node> q = new ArrayDeque<>();
	static boolean isFinish;

	static class node {
		int r, c, depth;

		public node(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Map = new char[N][M];
		minDepth = new int[N][M];
		for (int[] arr : minDepth)
			Arrays.fill(arr, 1557);
		MIN = Integer.MAX_VALUE;
		target = new int[4];
		isFinish = false;

		for (int i = 0; i < N; i++) { // @입력
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 0; j < M; j++) {
				Map[i][j] = str.charAt(j);
			}
		}
//		for (char[] a : Map) // @입력확인
//			System.out.println(Arrays.toString(a));
//		for (int[] a : minDepth) // @입력확인
//			System.out.println(Arrays.toString(a));

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 2; i++) {
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			target[2 * i + 0] = r;
			target[2 * i + 1] = c;
		}
//		System.out.println(Arrays.toString(target)); @입력확인

		minDepth[target[0]][target[1]] = 0;
		q.offer(new node(target[0], target[1], 0));
		bfs();
		if (isFinish)
			System.out.println(MIN);
		else
			System.out.println(-1);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			node cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				int nd = cur.depth + 1;

				for (int i = 0; i < K; i++) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						break;
					if (Map[nr][nc] == '#' || minDepth[nr][nc] < nd)
						break;
					if (minDepth[nr][nc] == nd)
						continue;
					if (nr == target[2] && nc == target[3]) {
						isFinish = true;
						MIN = Math.min(MIN, nd);
						return;
					}
					minDepth[nr][nc] = nd;
					q.offer(new node(nr, nc, nd));
				}
			}
		}
	}

}
