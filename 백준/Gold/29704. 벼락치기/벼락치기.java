import java.io.*;
import java.util.*;

public class Main {
	static int totalCost, N, T;
	static Node list[];

	static class Node {
		int time, cost;
		public Node(int time, int cost) {
			super();
			this.time = time;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		list = new Node[1 + N];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			totalCost += cost;
			list[i] = new Node(time, cost);
		}
		int maxCost = knapsack();
		System.out.println(totalCost - maxCost);
	}

	private static int knapsack() {
		int dp[][] = new int[1 + N][1 + T];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= T; j++) {
				dp[i][j] = dp[i - 1][j];
				if (list[i].time <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - list[i].time] + list[i].cost);
				}
			}
		}
		return dp[N][T];
	}
}
