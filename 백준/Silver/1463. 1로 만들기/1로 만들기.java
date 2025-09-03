import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[1000001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for (int num = 4; num <= N; num++) {
			if (num % 2 == 0)
				dp[num] = Math.min(dp[num], dp[num / 2] + 1);
			if (num % 3 == 0)
				dp[num] = Math.min(dp[num], dp[num / 3] + 1);
			dp[num] = Math.min(dp[num], dp[num - 1] + 1);
		}
		System.out.println(dp[N]);
	}
}
