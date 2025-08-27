import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] count_arr = new int[100001];
		Queue<Integer> queue = new ArrayDeque<>();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int max = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			queue.offer(tmp);
			if (++count_arr[tmp] > M) {
				while (true) {
					int K = queue.poll();
					count_arr[K]--;
					if (K == tmp) {
						break;
					}

				}
			}
			max = Math.max(max, queue.size());

		}
		System.out.println(max);

	}
}
