import java.io.*;
import java.util.*;

public class Main {
	static int target, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			target = Integer.parseInt(st.nextToken());
			cnt = 0;
			dfs(0);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int v) {
		if (v >= target) {
			if (v == target) {
				cnt++;
			}
			return;
		}
		dfs(v + 1);
		dfs(v + 2);
		dfs(v + 3);
	}
}