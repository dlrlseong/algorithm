import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int W, V;

		public Node(int w, int v) {
			super();
			W = w;
			V = v;
		}
	}

	static int N, K;
	static Node[] nodeArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nodeArr = new Node[1 + N];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			nodeArr[i] = new Node(W, V);
		}

		System.out.println(knapsack());
	}

	private static int knapsack() {
		int dp[][] = new int[1 + N][1 + K];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (nodeArr[i].W <= j) {
					dp[i][j] = Math.max(dp[i][j], nodeArr[i].V + dp[i - 1][j - nodeArr[i].W]);
				}
			}
		}
		return dp[N][K];
	}
}
