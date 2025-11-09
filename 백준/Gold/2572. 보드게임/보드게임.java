import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		char colors[] = new char[1 + N];
		for (int i = 1; i <= N; i++) {
			colors[i] = st.nextToken().charAt(0);
		}

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Edge> adjList[] = new ArrayList[1 + M];
		for (int i = 1; i <= M; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			char color = st.nextToken().charAt(0);

			adjList[from].add(new Edge(from, to, color));
			adjList[to].add(new Edge(to, from, color));
		}

		int dp[][] = new int[1 + N + 1][1 + M];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[1][1] = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int score = dp[i][j];
				if (score == -1)
					continue;

				for (Edge e : adjList[j]) {
					int to = e.to;
					int bonus = e.color == colors[i] ? 10 : 0;
					dp[i + 1][to] = Math.max(dp[i + 1][to], score + bonus);
				}
			}
		}

		int Max = 0;
		for (int i = 1; i <= M; i++) {
			Max = Math.max(Max, dp[N + 1][i]);
		}
		System.out.println(Max);

	}

	static class Edge {
		int from, to;
		char color;

		public Edge(int from, int to, char color) {
			super();
			this.from = from;
			this.to = to;
			this.color = color;
		}
	}
}
