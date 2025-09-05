import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Map[][] = new int[N][M];

		int Max = -1;

		Queue<int[]> q = new ArrayDeque<>();
		int zeroCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if (Map[i][j] == 2)
					q.offer(new int[] { i, j });
				if (Map[i][j] == 0)
					zeroCnt++;
			}
		}

		int tempMap[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(Map[i], 0, tempMap[i], 0, M);
		}

		for (int i = 0; i < M * N; i++) {
			int ir = i / M;
			int ic = i % M;
			if (Map[ir][ic] != 0)
				continue;
			for (int j = i + 1; j < M * N; j++) {
				int jr = j / M;
				int jc = j % M;
				if (Map[jr][jc] != 0)
					continue;
				for (int k = j + 1; k < M * N; k++) {
					int kr = k / M;
					int kc = k % M;
					if (Map[kr][kc] != 0)
						continue;
					Queue<int[]> tmpq = new ArrayDeque<>(q);
					tempMap[ir][ic] = tempMap[jr][jc] = tempMap[kr][kc] = 1;
					int cnt = zeroCnt - 3;

//					System.out.println(i + " " + j + " " + k);
					while (!tmpq.isEmpty()) {
						int cur[] = tmpq.poll();
						int cr = cur[0];
						int cc = cur[1];
						for (int d = 0; d < 4; d++) {
							int nr = cr + dr[d];
							int nc = cc + dc[d];

							if (nr < 0 || nc < 0 || nr >= N || nc >= M)
								continue;
							if (tempMap[nr][nc] != 0)
								continue;
							tempMap[nr][nc] = 2;
							cnt--;
							tmpq.offer(new int[] { nr, nc });
						}
					}
					Max = Math.max(Max, cnt);
					for (int l = 0; l < tempMap.length; l++) {
						System.arraycopy(Map[l], 0, tempMap[l], 0, M);
					}
//					for (int[] a : Map)
//						System.out.println(Arrays.toString(a));
				}
			}
		}
		System.out.println(Max);

	}
}
