import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static List<Integer> adjList[];
	static boolean invited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			invited = new boolean[1 + N];
			adjList = new ArrayList[1 + N];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from].add(to);
				adjList[to].add(from);
			}

			int cnt = 0;
			invited[1] = true;
			Deque<Integer> q = new ArrayDeque<>();
			for (int friend : adjList[1]) {
				q.offer(friend);
				invited[friend] = true;
				cnt++;
//				System.out.println("1 " + friend);
			}
			while (!q.isEmpty()) {
				int nf = q.poll();
				for (int friend : adjList[nf]) {
					if (invited[friend])
						continue;
					invited[friend] = true;
					cnt++;
//					System.out.println(nf + " " + friend);
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
