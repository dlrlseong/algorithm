import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		A: for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			String tmp = st.nextToken();
			tmp = tmp.substring(1, tmp.length() - 1);
			String[] nums = tmp.split(",");
			Deque<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				q.offer(Integer.parseInt(nums[i]));
			}
			int dir = 1;
			for (int i = 0; i < str.length(); i++) {
				char C = str.charAt(i);
				switch (C) {
				case 'R':
					dir = dir * -1;
					break;
				case 'D':
					if (q.isEmpty()) {
						sb.append("error").append("\n");
						continue A;
					}
					if (dir == 1) {
						q.pollFirst();
					} else {
						q.pollLast();
					}
					break;
				default:
					break;
				}
			}
			sb.append("[");
			boolean isEmpty = q.isEmpty();
			int qs = q.size();
			for (int i = 0; i < qs; i++) {
				if (dir == 1) {
					sb.append(q.pollFirst()).append(",");
				} else {
					sb.append(q.pollLast()).append(",");
				}
			}
			if (!isEmpty)
				sb.deleteCharAt(sb.length() - 1);
			sb.append("]").append("\n");
		}
		System.out.println(sb);
	}
}
