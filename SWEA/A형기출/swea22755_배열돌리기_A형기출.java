package algorithm;

import java.io.*;
import java.util.*;

public class swea22755_배열돌리기_A형기출 {
	static int N, M, K, numbers[], MIN;
	static int Arr[][], saveArr[][];
	static List<int[]> list;
	static final int dr[] = { -1, 1, 0, 0 };
	static final int dc[] = { 0, 0, -1, 1 };
	static boolean vis[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			////////////////////////////////////////////////
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			numbers = new int[K];
			vis = new boolean[K];

			Arr = new int[N][M];
			saveArr = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					Arr[i][j] = Integer.parseInt(st.nextToken());
					saveArr[i][j] = Arr[i][j];
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list.add(new int[] { n - 1, m - 1, s });
			}

			MIN = Integer.MAX_VALUE;
			Perm(0);

			////////////////////////////////////////////////
			sb.append(MIN).append("\n");
		}
		System.out.println(sb);
	}

	private static void Perm(int cnt) {
		if (cnt == K) {
			for (int i = 0; i < N; i++) {
				System.arraycopy(saveArr[i], 0, Arr[i], 0, M);
			}

			for (int i = 0; i < K; i++) {
				int[] command = list.get(numbers[i]);
				rotateArray(command[0], command[1], command[2]);
			}
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += Arr[i][j];
				}
				MIN = Math.min(MIN, sum);
			}
			return;
		}

		for (int i = 0; i < K; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			numbers[cnt] = i;
			Perm(cnt + 1);
			vis[i] = false;
		}
	}

	private static void rotateArray(int n, int m, int s) {
		if (s == 0)
			return;

		int tmp = Arr[n - s][m - s];
		for (int c = m - s + 1; c <= m + s; c++) {
			int r = n - s;
			int k = Arr[r][c];
			Arr[r][c] = tmp;
			tmp = k;
		}

		for (int r = n - s + 1; r <= n + s; r++) {
			int c = m + s;
			int k = Arr[r][c];
			Arr[r][c] = tmp;
			tmp = k;
		}

		for (int c = m + s - 1; c >= m - s; c--) {
			int r = n + s;
			int k = Arr[r][c];
			Arr[r][c] = tmp;
			tmp = k;
		}

		for (int r = n + s - 1; r >= n - s; r--) {
			int c = m - s;
			int k = Arr[r][c];
			Arr[r][c] = tmp;
			tmp = k;
		}

		rotateArray(n, m, s - 1);
	}

}
