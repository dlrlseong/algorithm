package algorithm;

import java.io.*;
import java.util.*;

public class swea22756_파이프옮기기_A형기출 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int Map[][] = new int[1 + N][1 + N];
			int dp[][][] = new int[1 + N][1 + N][3];

			for (int i = 1; i <= N; i++) { // @입력
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[1][2][0] = 1;
			for (int i = 1; i <= N; i++) { // @dp계산
				for (int j = 3; j <= N; j++) {
					if (Map[i][j] == 1)
						continue;
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
					if (Map[i - 1][j] == 0 && Map[i][j - 1] == 0) {
						dp[i][j][2] += dp[i - 1][j - 1][0];
						dp[i][j][2] += dp[i - 1][j - 1][1];
						dp[i][j][2] += dp[i - 1][j - 1][2];
					}
				}
			}
			// @출력
			int sum = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

}
