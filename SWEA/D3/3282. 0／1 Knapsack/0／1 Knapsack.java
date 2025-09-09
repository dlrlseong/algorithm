import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int dp[][] = new int[1 + N][1 + K];
			Arrays.fill(dp[0], 0);
			for (int i = 1; i <= N; i++) {
				dp[i][0] = 0;
			}

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				for (int j = 1; j <= K; j++) {
					if (v > j) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(p + dp[i - 1][j - v], dp[i - 1][j]);
					}
				}
			}
//			for (int[] a : dp)
//				System.out.println(Arrays.toString(a));
//			System.out.println(dp[N][K]);
			sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}

}
