import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean isVisted[] = new boolean[100001];

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		int s = 0;
		while (true) {
			int qs = q.size();
			for (int i = 0; i < qs; i++) {
				int n = q.poll();
				while (n <= 100000) {
//					System.out.println(n);
					if (n == K) {
						System.out.println(s);
						return;
					}
					if (isRange(n - 1) && !isVisted[n - 1]) {
						isVisted[n - 1] = true;
						q.offer(n - 1);
					}
					if (isRange(n + 1) && !isVisted[n + 1]) {
						isVisted[n + 1] = true;
						q.offer(n + 1);
					}
					if (n == 0)
						break;
					n = n * 2;
				}
			}
			s++;
		}
	}

	private static boolean isRange(int i) {
		return 0 <= i && i <= 100000;
	}
}