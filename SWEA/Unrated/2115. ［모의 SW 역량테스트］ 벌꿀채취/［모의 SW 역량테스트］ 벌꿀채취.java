import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, C;
	static int[][] Map, sumMap;
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Map = new int[N][N];
			dp = new int[1 + M][1 + C];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int Max = -1;
			for (int i = 0; i < N * N; i++) {
				for (int j = i + 1; j < N * N; j++) {
					int p[][] = new int[2][2];
					p[0] = new int[] { i / N, i % N };
					p[1] = new int[] { j / N, j % N };
					if (p[0][1] > N - M || p[1][1] > N - M)
						continue;
					if (p[0][0] == p[1][0] && p[1][1] - p[0][1] < M)
						continue;
					int price = 0;
					for (int k = 0; k < 2; k++) {
						int tmpPrice = 0;
						int arr[] = new int[M];
						System.arraycopy(Map[p[k][0]], p[k][1], arr, 0, M);
						tmpPrice = knapsack(arr);
						price += tmpPrice;
					}
					Max = Math.max(Max, price);
				}
			}
			sb.append("#").append(tc).append(" ").append(Max).append("\n");
		}
		System.out.println(sb);
	}

	private static int knapsack(int[] arr) {
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= C; j++) {
				int cost = arr[i - 1];

				dp[i][j] = dp[i - 1][j];

				if (cost <= j)
					dp[i][j] = Math.max(dp[i][j], cost * cost + dp[i - 1][j - cost]);
			}
		}
//		for (int[] a : dp2)
//			System.out.println(Arrays.toString(a));
//		System.out.println();
		return dp[M][C];
	}

}
