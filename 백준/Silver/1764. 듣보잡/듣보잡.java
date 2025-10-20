import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			set.add(str);
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			if (set.contains(str)) {
				cnt++;
				list.add(str);
			}
		}

		Collections.sort(list);
		for (String s : list) {
			sb.append(s).append("\n");
		}
		System.out.println(cnt);
		System.out.println(sb);

	}
}
