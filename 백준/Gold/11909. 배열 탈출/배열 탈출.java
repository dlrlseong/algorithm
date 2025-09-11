import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int arr[][] = new int[1 + N][1 + N];
		int dp[][] = new int[1 + N][1 + N];
		Arrays.fill(arr[0], Integer.MAX_VALUE);
		Arrays.fill(dp[0], Integer.MAX_VALUE);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j <= N; j++) {
				if (j == 0) {
					arr[i][j] = Integer.MAX_VALUE;
					dp[i][j] = Integer.MAX_VALUE;
				} else {
					arr[i][j] = Integer.parseInt(st.nextToken());
					int rCost = Math.max(0, arr[i][j] - arr[i - 1][j] + 1);
					int cCost = Math.max(0, arr[i][j] - arr[i][j - 1] + 1);
					dp[i][j] = Math.min(dp[i - 1][j] + rCost, dp[i][j - 1] + cCost);
					dp[1][1] = 0;
				}
			}
		}
		System.out.println(dp[N][N]);
	}
}
