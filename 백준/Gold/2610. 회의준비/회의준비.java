import java.io.*;
import java.util.*;

public class Main {
	static int N, M, P[], Time[], idxTime[];
	static boolean vis[];
	static List<Integer> adjList[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[1 + N]; // @init
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		make();
		for (int i = 0; i < M; i++) { // @input
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);

			// @union find
			union(from, to);
		}
//		System.out.println(Arrays.toString(P)); // @test

		// @bfs
		Time = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			vis = new boolean[1 + N];
			int time = bfs(i);
			Time[i] = time;
		}
//		System.out.println(Arrays.toString(Time)); // @test

		idxTime = new int[1 + N];
		for (int i = 1; i <= N; i++) {
			if (idxTime[find(i)] == 0) {
				idxTime[find(i)] = i;
			} else {
				if (Time[idxTime[find(i)]] > Time[i])
					idxTime[find(i)] = i;
			}
		}
//		System.out.println(Arrays.toString(idxTime)); // @test

		int cnt = 0;
		List<Integer> rep = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (idxTime[i] == 0)
				continue;
			cnt++;
			rep.add(idxTime[i]);
		}
		Collections.sort(rep);
		sb.append(cnt).append("\n");
		for (int a : rep) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int i) {
		Deque<int[]> q = new ArrayDeque<>();
		vis[i] = true;
		q.offerLast(new int[] { i, 1 });
		int depth = 0;
		while (!q.isEmpty()) {
			int[] cur = q.pollFirst();
			depth = cur[1];
			for (int j = 0; j < adjList[cur[0]].size(); j++) {
				if (vis[adjList[cur[0]].get(j)])
					continue;
				vis[adjList[cur[0]].get(j)] = true;
				q.offerLast(new int[] { adjList[cur[0]].get(j), cur[1] + 1 });
			}
		}
		return depth;
	}

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
		if (find(a) == find(b))
			return false;
		P[find(b)] = find(a);
		return true;
	}
}