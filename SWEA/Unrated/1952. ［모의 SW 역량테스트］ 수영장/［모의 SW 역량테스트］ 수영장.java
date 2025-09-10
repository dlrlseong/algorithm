import java.io.*;
import java.util.*;

public class Solution {
	static int YEAR, MONTH, DAY, MONTH3;
	static int plan[];
	static int minCost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			plan = new int[1 + 12];
			st = new StringTokenizer(br.readLine(), " ");
			DAY = Integer.parseInt(st.nextToken());
			MONTH = Integer.parseInt(st.nextToken());
			MONTH3 = Integer.parseInt(st.nextToken());
			YEAR = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			minCost = YEAR;
			dfs(1, 0);
			sb.append("#").append(tc).append(" ").append(minCost).append("\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int nthMonth, int cost) {
		if (nthMonth == 13) {
			minCost = Math.min(minCost, cost);
			return;
		}
		if (cost > minCost)	return;
		if (nthMonth + 2 <= 12) 
			dfs(nthMonth + 3, cost + MONTH3);
		dfs(nthMonth + 1, cost + MONTH);
		dfs(nthMonth + 1, cost + DAY * plan[nthMonth]);
	}
}