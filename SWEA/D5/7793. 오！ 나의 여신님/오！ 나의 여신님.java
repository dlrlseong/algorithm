import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, devilMap[][], myMap[][];
	static boolean vis[][], isOver;
	static char Map[][];
	static int MIN;

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

			Map = new char[N][M];
			vis = new boolean[N][M];
			devilMap = new int[N][M];
			myMap = new int[N][M];

			int me[] = new int[2];
			Deque<int[]> q = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String str = st.nextToken();
				for (int j = 0; j < M; j++) {
					Map[i][j] = str.charAt(j);
					if (Map[i][j] == '*') {
						q.offer(new int[] { i, j });
						vis[i][j] = true;
					} else if (Map[i][j] == 'S') {
						me[0] = i;
						me[1] = j;
					}
				}
			}
			MIN = Integer.MAX_VALUE;
			isOver = false;
			bfs_devil(q);
//			for (int[] a : devilMap) {
//				System.out.println(Arrays.toString(a));
//			}
			bfs_me(me);
//			for (int[] a : myMap) {
//				System.out.println(Arrays.toString(a));
//			}

			if (!isOver)
				sb.append("#").append(tc).append(" ").append("GAME OVER").append("\n");
			else
				sb.append("#").append(tc).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs_devil(Deque<int[]> q) {
		// 초기화 부분
		for (int i = 0; i < N; i++) {
		    Arrays.fill(devilMap[i], Integer.MAX_VALUE);
		}
		int dist = -1;
		while (!q.isEmpty()) {
			int size = q.size();
			dist++;
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				devilMap[cur[0]][cur[1]] = dist;
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						continue;
					if (Map[nr][nc] == 'X' || Map[nr][nc] == 'D')
						continue;
					if (vis[nr][nc])
						continue;
					vis[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static void bfs_me(int[] me) {
		Deque<int[]> q = new ArrayDeque<>();
		vis = new boolean[N][M];
		vis[me[0]][me[1]] = true;
		q.offer(me);
		int dist = -1;
		while (!q.isEmpty()) {
			int size = q.size();
			dist++;
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				myMap[cur[0]][cur[1]] = dist;
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						continue;
					if (vis[nr][nc])
						continue;
					if (Map[nr][nc] == 'D') {
						MIN = Math.min(MIN, dist + 1);
						isOver = true;
						return;
					}
					if (Map[nr][nc] == 'X' || devilMap[nr][nc] <= dist + 1)
						continue;
					vis[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

}
