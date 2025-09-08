package algorithm;
import java.io.*;
import java.util.*;

public class swea18565_풍선사격게임_A형기출 {
	static int N, Max, arr[], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			arr = new int[1 + N + 1];
			dp = new int[1 + N][1 + N];
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i], -1);
			}
			arr[0] = 1;
			arr[N + 1] = 1;

			for (int len = 1; len <= N; len++) {
				for (int l = 1; l <= N - len + 1; l++) {
					int r = l + len - 1;

					for (int k = l; k <= r; k++) {
						int currentScore = 0;

						if (k - 1 >= l) {
							currentScore += dp[l][k - 1];
						}

						if (k + 1 <= r) {
							currentScore += dp[k + 1][r];
						}

						int leftVal = arr[l - 1];
						int rightVal = arr[r + 1];

						if (l == 1 && r == N) {
							currentScore += arr[k];
						} else {
							currentScore += leftVal * rightVal;
						}

						dp[l][r] = Math.max(dp[l][r], currentScore);
					}

				}
			}
			sb.append('#').append(tc).append(' ').append(dp[1][N]).append('\n');
		}
//		System.out.println(dp[1][N]);
		System.out.println(sb);
	}
}
