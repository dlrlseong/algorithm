import java.io.*;
import java.util.*;

public class Main {
	static int N, M, p[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		make();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int C = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (C == 0) {
				union(A, B);
			} else {
				if (find(A) == find(B)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb);

	}

	private static void make() {
		p = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	private static int find(int a) {
		if (p[a] == a)
			return a;
		else
			return p[a] = find(p[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		// union
		p[find(b)] = find(a);
		return true;
	}

}
