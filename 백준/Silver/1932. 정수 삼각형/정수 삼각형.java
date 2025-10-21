import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());

		@SuppressWarnings("unchecked")
		List<Integer> dp[] = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			dp[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine(), " ");
		dp[0].add(Integer.parseInt(st.nextToken()));

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmp = Integer.parseInt(st.nextToken());
			dp[i].add(dp[i - 1].get(0) + tmp);
			for (int j = 1; j < i; j++) {
				tmp = Integer.parseInt(st.nextToken());
				int A = dp[i - 1].get(j);
				int B = dp[i - 1].get(j - 1);
				dp[i].add(Math.max(A, B) + tmp);
			}
			tmp = Integer.parseInt(st.nextToken());
			dp[i].add(dp[i - 1].get(i - 1) + tmp);
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(dp[i]);
//		}
		Collections.sort(dp[N - 1], Collections.reverseOrder());
		System.out.println(dp[N - 1].get(0));
	}
}