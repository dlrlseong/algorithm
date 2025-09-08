package algorithm;
import java.io.*;
import java.util.*;

public class swea23802_화분에비료주기_A형기출 {
	static int n, p;
	static int a[], b[];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			a = new int[n];
			b = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			dp = new int[n][2];

			dp[0][0] = a[0];
			dp[0][1] = b[0];

			for (int i = 1; i < n; i++) {
				// a비료를 줬을 때
				int a_a = dp[i - 1][0] + Math.max(0, a[i] - p);
				int b_a = dp[i - 1][1] + a[i];
				dp[i][0] = Math.max(a_a, b_a);
				// b비료를 줬을 때
				int a_b = dp[i - 1][0] + b[i];
				int b_b = dp[i - 1][1] + Math.max(0, b[i] - p);
				dp[i][1] = Math.max(a_b, b_b);
			}

			System.out.println("#" + tc + " " + Math.max(dp[n - 1][0], dp[n - 1][1]));
		}

	}

}
