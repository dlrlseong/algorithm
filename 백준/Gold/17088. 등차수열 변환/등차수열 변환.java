import java.io.*;
import java.util.*;

public class Main {
	static int MIN = Integer.MAX_VALUE;
	static int N, arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, 0, 0, arr[0]);
		dfs(1, 0, 1, arr[0] - 1);
		dfs(1, 0, 1, arr[0] + 1);

		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}

	public static void dfs(int depth, int diff, int cnt, int prev) {
		if (cnt > MIN)
			return;

		if (depth == N) {
			MIN = Math.min(MIN, cnt);
			return;
		}

		if (depth == 1) {
			dfs(depth + 1, arr[depth] - prev, cnt, arr[depth]);
			dfs(depth + 1, arr[depth] - 1 - prev, cnt + 1, arr[depth] - 1);
			dfs(depth + 1, arr[depth] + 1 - prev, cnt + 1, arr[depth] + 1);
		}

		if (diff == arr[depth] - prev) {
			dfs(depth + 1, diff, cnt, arr[depth]);
		}
		if (diff == arr[depth] - 1 - prev) {
			dfs(depth + 1, diff, cnt + 1, arr[depth] - 1);
		}
		if (diff == arr[depth] + 1 - prev) {
			dfs(depth + 1, diff, cnt + 1, arr[depth] + 1);
		}

	}
}
