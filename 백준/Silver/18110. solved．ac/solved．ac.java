import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		List<Integer> scores = new ArrayList<>();
		double sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmp = Integer.parseInt(st.nextToken());
			scores.add(tmp);
			sum += tmp;
		}
		Collections.sort(scores);
		int k = (int) Math.round(N * 0.15);
		for (int i = 0; i < k; i++) {
			sum -= scores.get(i);
			sum -= scores.get(N - 1 - i);
		}
		System.out.println(Math.round(sum / (N - 2 * k)));
	}
}