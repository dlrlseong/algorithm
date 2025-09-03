import java.io.*;
import java.util.*;

public class Solution {

	static int N;
	static int[] balloons;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			balloons = new int[1 + N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				balloons[i] = Integer.parseInt(st.nextToken());
			}
			balloons[0] = 1;
			balloons[N + 1] = 1;

			dp = new int[1 + N][1 + N];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(dp[i], -1);
			}

			int maxScore = solve(1, N);
			sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
		}
		System.out.print(sb);
		br.close();
	}

	private static int solve(int start, int end) {
		if (start > end) {
			return 0;
		}

		if (dp[start][end] != -1) {
			return dp[start][end];
		}

		int maxScore = 0;
		for (int k = start; k <= end; k++) {
			int currentScore = solve(start, k - 1) + solve(k + 1, end);
			
			int leftVal = balloons[start - 1];
			int rightVal = balloons[end + 1];

			if (start == 1 && end == N) {
				currentScore += balloons[k];
			} else {
				currentScore += leftVal * rightVal;
			}

			maxScore = Math.max(maxScore, currentScore);
		}

		return dp[start][end] = maxScore;
	}
}