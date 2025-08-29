import java.io.*;
import java.util.*;

/*
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] inDegrees = new int[N + 1];
		boolean[] isVisited = new boolean[N + 1];

		List<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			inDegrees[to]++;
			graph[from].add(to);
		}

		int visited = 0;

		while (visited < N) {
			int visit_num = -1;
			int inDegree = 0;
			while (visit_num == -1) {
				for (int i = 1; i <= N; i++) {
					if (isVisited[i])
						continue;
					if (inDegrees[i] == inDegree) {
						visit_num = i;
						isVisited[i] = true;
						break;
					}
				}
				inDegree++;
			}
			sb.append(visit_num).append(" ");
//			System.out.println(visit_num);
			for (int i = 0; i < graph[visit_num].size(); i++) {
				inDegrees[graph[visit_num].get(i)]--;
			}
			visited++;
		}
		System.out.println(sb);
	}

}
