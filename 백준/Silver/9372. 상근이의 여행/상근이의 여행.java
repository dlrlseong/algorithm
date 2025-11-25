import java.io.*;
import java.util.*;

public class Main {

	static int P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			P = new int[1 + N];
			makeSet(N);

			int cnt = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (union(a, b))
					cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void makeSet(int n) {
		P = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			P[i] = i;
		}
	}

	private static int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		if (rootA < rootB) {
			P[rootB] = rootA;
		} else {
			P[rootA] = rootB;
		}
		return true;

	}
}
