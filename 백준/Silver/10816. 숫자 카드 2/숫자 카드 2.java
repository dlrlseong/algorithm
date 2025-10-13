import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> map = new HashMap<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (map.containsKey(tmp)) {
				map.put(tmp, map.get(tmp) + 1);
			} else {
				map.put(tmp, 1);
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (map.containsKey(tmp)) {
				sb.append(map.get(tmp)).append(" ");
			} else {
				sb.append("0 ");
			}
		}
		System.out.println(sb);
	}
}