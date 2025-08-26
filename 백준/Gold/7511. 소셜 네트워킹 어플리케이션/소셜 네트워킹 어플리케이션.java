import java.io.*;
import java.util.*;

public class Main {
	static int N, K, M, p[];

	public static void make() {
		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	public static int find(int a) {
		if (p[a] == a)
			return a;
		return p[a] = find(p[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		p[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("Scenario ").append(tc).append(":\n");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 정점 수 N
			make();
			st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken()); // 간선 수 K
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 입력 수 M
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (find(a) == find(b))
					sb.append("1\n");
				else
					sb.append("0\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
