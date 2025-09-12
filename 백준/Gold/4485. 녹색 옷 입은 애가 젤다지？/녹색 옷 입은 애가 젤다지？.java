import java.io.*;
import java.util.*;

public class Main {
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			int[][] graph = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dist = dijkstra(graph, N);
			sb.append("Problem ").append(cnt++).append(": ").append(dist[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);

	}

	private static int[][] dijkstra(int[][] graph, int N) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][0] = graph[0][0];
		pq.offer(new int[] { 0, 0, dist[0][0] });

		while (!pq.isEmpty()) {
			int cur[] = pq.poll();
			int r = cur[0];
			int c = cur[1];

			if (dist[r][c] < cur[2])
				continue;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (dist[nr][nc] > cur[2] + graph[nr][nc]) {
					dist[nr][nc] = cur[2] + graph[nr][nc];
					pq.offer(new int[] { nr, nc, cur[2] + graph[nr][nc] });
				}
			}
		}
		return dist;
	}

}
