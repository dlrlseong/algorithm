import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		Deque<Integer> q = new ArrayDeque<>();

		int N = Integer.parseInt(st.nextToken());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			switch (str) {
			case "push":
				int X = Integer.parseInt(st.nextToken());
				q.offer(X);
				break;
			case "pop":
				if (q.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(q.poll()).append("\n");
				}
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				sb.append(q.isEmpty() ? 1 : 0).append("\n");
				break;
			case "front":
				if (q.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(q.peekFirst()).append("\n");
				}
				break;
			case "back":
				if (q.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(q.peekLast()).append("\n");
				}
				break;

			default:
				break;
			}
		}
		System.out.println(sb);
	}
}