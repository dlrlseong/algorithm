import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static boolean[][] visited;
	static int N;
	static int M;

	static void bfs(int i, int j) {

		// 8방 탐색
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] { i, j });
		visited[i][j] = true;

		while (q.size() > 0) {
			int[] tmp = q.pollFirst();
			int r = tmp[0];
			int c = tmp[1];


			for (int k = 0; k < 8; k++) {
				int rk = r + dr[k];
				int ck = c + dc[k];

				if (rk < 0 || ck < 0 || rk > N - 1 || ck > M - 1 || visited[rk][ck] == true) {
					continue;
				}
				if (arr[rk][ck] == 1) {
					q.offerLast(new int[] { rk, ck });
					visited[rk][ck] = true;
				} else {
					visited[rk][ck] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == true)
					continue;
				else {
					if (arr[i][j] == 0)
						visited[i][j] = true;
					else {
						bfs(i, j);
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);

	}
}
