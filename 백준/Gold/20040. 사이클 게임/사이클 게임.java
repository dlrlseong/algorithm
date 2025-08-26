import java.io.*;
import java.util.*;

public class Main {
	static int N, M, P[];
	static boolean isOver;

	public static void make() {
		P = new int[N];
		for (int i = 0; i < N; i++) {
			P[i] = i;
		}
	}

	public static int find(int a) {
		if (P[a] == a)
			return a;
		return P[a] = find(P[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		P[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		make();
		isOver = false;
		int num = 0;

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if (isOver)
				continue;

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (!union(from, to)) {
				isOver = true;
				num = i;
			}
		}

		System.out.println(num);

	}
}
