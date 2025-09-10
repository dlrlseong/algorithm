import java.io.*;
import java.util.*;

public class Main {
	static int cnt[] = new int[1 + 5];
	static int MIN = Integer.MAX_VALUE;
	static int arr[][] = new int[10][10];
	static boolean vis[][] = new boolean[10][10];
	static boolean isPossible = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		int output = isPossible ? MIN : -1;
		System.out.println(output);
	}

	public static void dfs(int r, int c) {
		if (r == 10) {
			isPossible = true;
			MIN = Math.min(MIN, cnt[1] + cnt[2] + cnt[3] + cnt[4] + cnt[5]);
			return;
		}

		if (cnt[1] + cnt[2] + cnt[3] + cnt[4] + cnt[5] > MIN)
			return;

		if (arr[r][c] == 0 || vis[r][c]) {
			dfs(r + ((c + 1) / 10), (c + 1) % 10);
			return;
		}
		for (int size = 5; size >= 1; size--) {
			if (cnt[size] < 5 && isPossibleSize(r, c, size)) {
				action(r, c, size, true);
				dfs(r + ((c + 1) / 10), (c + 1) % 10);
				action(r, c, size, false);
			}
		}
	}

	private static void action(int r, int c, int size, boolean go) {
		cnt[size] += go ? 1 : -1;
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				vis[i][j] = go;
			}
		}

//		System.out.println(r + " " + c + " " + size + " " + go + " " + cnt[size]);
	}

	private static boolean isPossibleSize(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (i >= 10 || j >= 10 || vis[i][j])
					return false;
				if (arr[i][j] == 0)
					return false;
			}
		}
		return true;
	}
}
