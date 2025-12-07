import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());

		@SuppressWarnings("unchecked")
		List<Integer> adjList[] = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		int root = -1;
		st = new StringTokenizer(br.readLine(), " ");
		int pArr[] = new int[N];
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			pArr[i] = p;

			if (p == -1) {
				root = i;
				continue;
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());

		if (D == root) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (i == D || i == root)
				continue;
			adjList[pArr[i]].add(i);
		}

		int cnt = 0;
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (adjList[cur].size() == 0) {
				cnt++;
			} else {
				for (Integer n : adjList[cur]) {
					q.offer(n);
				}
			}

		}
		System.out.println(cnt);

	}

}
