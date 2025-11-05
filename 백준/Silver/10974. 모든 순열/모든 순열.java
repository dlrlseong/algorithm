import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int numbers[];
	static boolean vis[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		vis = new boolean[N];
		Perm(N, 0);
		System.out.print(sb);
	}

	private static void Perm(int n, int depth) {
		if (depth == n) {
			for (int i = 0; i < n; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < n; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			numbers[depth] = i + 1;
			Perm(n, depth + 1);
			vis[i] = false;
		}

	}
}