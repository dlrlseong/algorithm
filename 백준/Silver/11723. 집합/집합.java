import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new HashSet<>();

		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			switch (str) {
			case "add":
				int tmp = Integer.parseInt(st.nextToken());
				if (!set.contains(tmp)) {
					set.add(tmp);
				}
				break;
			case "remove":
				tmp = Integer.parseInt(st.nextToken());
				if (set.contains(tmp)) {
					set.remove(tmp);
				}
				break;
			case "check":
				tmp = Integer.parseInt(st.nextToken());
				if (!set.contains(tmp)) {
					sb.append(0).append("\n");
				} else {
					sb.append(1).append("\n");
				}
				break;
			case "toggle":
				tmp = Integer.parseInt(st.nextToken());
				if (set.contains(tmp)) {
					set.remove(tmp);
				} else {
					set.add(tmp);
				}
				break;
			case "all":
				set.clear();
				for (int j = 1; j <= 20; j++) {
					set.add(j);
				}
				break;
			case "empty":
				set.clear();
				break;

			default:
				break;
			}
		}
		System.out.println(sb);
	}

}
