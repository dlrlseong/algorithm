import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int dp[][] = new int[1 + k][1 + n];

			for (int i = 1; i <= n; i++) {
				dp[0][i] = i;
			}

			for (int i = 1; i <= k; i++) {
				for (int j = 1; j <= n; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}

			sb.append(dp[k][n]).append("\n");
		}
		System.out.println(sb);
	}
}