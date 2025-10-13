import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			List<Integer> valueList = new ArrayList<>();
			Queue<int[]> queue = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int value = Integer.parseInt(st.nextToken());
				queue.offer(new int[] { i, value });
				valueList.add(value);
			}
			Collections.sort(valueList, Collections.reverseOrder());
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int curNum = cur[0];
				int curValue = cur[1];
				if (curValue == valueList.get(0)) {
					if (curNum == target) {
						sb.append(N - queue.size()).append("\n");
						break;
					}
					valueList.remove(0);
				} else {
					queue.offer(cur);
				}
			}
		}
		System.out.println(sb);
	}
}