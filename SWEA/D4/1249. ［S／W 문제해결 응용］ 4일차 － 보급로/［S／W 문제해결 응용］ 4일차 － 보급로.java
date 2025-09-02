import java.io.*;
import java.util.*;

public class Solution {
	static int N, Map[][];
	static final int INF = Integer.MAX_VALUE;
	static final int dr[] = { -1, 1, 0, 0 };
	static final int dc[] = { 0, 0, -1, 1 };

	private static int dijkstra(int startR, int startC) {

		int minCost[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];

		for (int[] arr : minCost)
			Arrays.fill(arr, INF);

		minCost[startR][startC] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		visited[startR][startC] = true;
		pq.offer(new int[] { startR, startC, minCost[startR][startC] });

		while (!pq.isEmpty()) {
			int now[] = pq.poll();
			int r = now[0];
			int c = now[1];
			int cost = now[2];

			if (r == N - 1 && c == N - 1) {
				return cost;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]
						&& minCost[nr][nc] > cost + Map[nr][nc]) {
					minCost[nr][nc] = cost + Map[nr][nc];
					visited[nr][nc] = true;
					pq.offer(new int[] { nr, nc, minCost[nr][nc] });
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			Map = new int[N][N];

			for (int i = 0; i < N; i++) { // @입력
				st = new StringTokenizer(br.readLine(), " ");
				String str = st.nextToken();
				for (int j = 0; j < N; j++) {
					Map[i][j] = str.charAt(j) - '0';
				}
			}
//			for(int[] a :Map) System.out.println(Arrays.toString(a)); //@입력확인 이상무
			sb.append("#").append(tc).append(" ").append(dijkstra(0, 0)).append("\n");
		}
		System.out.println(sb);
	}
}
