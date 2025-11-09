import java.io.*;
import java.util.*;

public class Main {
	static int N, p[], min;
	static boolean isA[], isB[];
	static List<Integer> adjList[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		p = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[i].add(to - 1);
			}
		}
		isA = new boolean[N];
		isB = new boolean[N];
		min = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void dfs(int depth) {
		if (depth == N) {
			int aboutA = isPossible(isA);
			int aboutB = isPossible(isB);
			if (aboutA == -1 || aboutB == -1) {
				return;
			}
			min = Math.min(min, Math.abs(aboutA - aboutB));
			return;
		}

		isA[depth] = true;
		dfs(depth + 1);
		isA[depth] = false;

		isB[depth] = true;
		dfs(depth + 1);
		isB[depth] = false;
	}

	private static int isPossible(boolean[] is) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (is[i] == true)
				cnt++;
		}
		if (cnt == 0 || cnt == N)
			return -1;

		int idx = -1;
		for (int i = 0; i < N; i++) {
			if (is[i]) {
				idx = i;
				break;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		boolean vis[] = new boolean[N];
		q.add(idx);
		vis[idx] = true;
		int cnt2 = 0;
		int sum = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			cnt2++;
			sum += p[cur];
			for (Integer next : adjList[cur]) {
				if (vis[next] || !is[next])
					continue;
				vis[next] = true;
				q.offer(next);
			}
		}
		if (cnt == cnt2) {
			return sum;
		}
		return -1;
	}
}
