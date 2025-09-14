import java.io.*;
import java.util.*;

/*
 *  x x -> o x | x o | x x -> 3가지
 *  x o -> o x | x x -> 2가지
 *  o x -> x o | x x -> 2가지
 *  
 *   1,1,1 -> 1+1+1, 1+1, 1+1 -> 3+2+2, 3+2, 3+2 -> 7+5+5, 7+5, 7+5 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int dp[][] = new int[1 + N][3];
		dp[1][0] = dp[1][1] = dp[1][2] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
		}
		System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
	}

}
