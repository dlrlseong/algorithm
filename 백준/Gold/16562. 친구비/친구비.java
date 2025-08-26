import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, k, p[], cost[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		cost = new int[1 + N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (cost[find(from)] >= cost[find(to)])
				union(to, from);
			else
				union(from, to);
		}

//		System.out.println("cost: " + Arrays.toString(cost));
//		for (int i = 0; i <= N; i++) {
//			System.out.println("i: " + i + " find(i): " + find(i) + " cost[find(i)]: " + cost[find(i)]);
//		}

		for (int i = 1; i <= N; i++) {
			if (find(i) == 0) // 이미 친구니?
				continue;
			cost[0] += cost[find(i)];
			union(0, i);
		}

		if (cost[0] > k)
			System.out.println("Oh no");
		else
			System.out.println(cost[0]);

	}

	private static void make() {
		p = new int[1 + N];
		for (int i = 0; i <= N; i++) {
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
