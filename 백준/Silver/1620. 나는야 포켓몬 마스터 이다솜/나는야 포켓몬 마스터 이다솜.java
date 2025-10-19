import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("PAD");

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			map.put(str, i);
			list.add(str);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			if (map.containsKey(str)) {
				sb.append(map.get(str)).append("\n");
			} else {
				sb.append(list.get(Integer.parseInt(str))).append("\n");
			}
		}

		System.out.println(sb);
	}

}
