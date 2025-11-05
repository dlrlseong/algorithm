import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		list.add(1);
		set.add(1);

		list.add(5);
		set.add(5);

		list.add(10);
		set.add(10);

		list.add(50);
		set.add(50);

		for (int i = 1; i < N; i++) {
			int ls = list.size();
			set.clear();
			for (int j = 0; j < ls; j++) {
				int n = list.remove(0);

				int tmp = n + 1;
				if (!set.contains(tmp)) {
					list.add(tmp);
					set.add(tmp);
				}
				tmp = n + 5;
				if (!set.contains(tmp)) {
					list.add(tmp);
					set.add(tmp);
				}
				tmp = n + 10;
				if (!set.contains(tmp)) {
					list.add(tmp);
					set.add(tmp);
				}
				tmp = n + 50;
				if (!set.contains(tmp)) {
					list.add(tmp);
					set.add(tmp);
				}
			}
		}
//		System.out.println(list);
		System.out.println(set.size());
	}
}