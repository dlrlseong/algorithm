import java.io.*;
import java.util.*;

public class Main {
	static long A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		bfs(A);
	}

	private static void bfs(long N) {
		Queue<Long> q = new ArrayDeque<>();
		q.offer(N);
		int cnt = 0;
		while (!q.isEmpty()) {
			int qs = q.size();
			cnt++;
			for (int i = 0; i < qs; i++) {
				long cur = q.poll();
				long tmp = cur * 2;
				if (tmp == B) {
					System.out.println(cnt + 1);
					return;
				}
				if (tmp < B) {
					q.offer(tmp);
				}

				String str = Long.toString(cur);
				str = str + "1";
				tmp = Long.parseLong(str);
				if (tmp == B) {
					System.out.println(cnt + 1);
					return;
				}
				if (tmp < B) {
					q.offer(tmp);
				}
			}
		}
		System.out.println(-1);
	}
}