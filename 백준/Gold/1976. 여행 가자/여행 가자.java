import java.io.*;
import java.util.*;

public class Main {
	static int N, M, P[];

	public static void make() {
		P = new int[1 + N];
		for (int i = 1; i <= N; i++) {
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

	public static boolean isConnected(int a, int b) {
		if (find(a) == find(b))
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		make();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		boolean isConnected = false;
		int A = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M - 1; i++) {
			int B = Integer.parseInt(st.nextToken());
			isConnected = isConnected(A, B);
			if (!isConnected) {
				System.out.println("NO");
				return;
			}
			A = B;
		}
		System.out.println("YES");
	}
}
