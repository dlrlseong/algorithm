import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		List<Integer> list = new ArrayList<>();
		while (!q.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				q.offer(q.poll());
			}
			list.add(q.poll());
		}
		for (Integer n : list) {
			sb.append(n).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println("<" + sb + ">");
	}
}