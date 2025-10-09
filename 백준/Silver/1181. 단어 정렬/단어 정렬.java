import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<>();
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			set.add(st.nextToken());
		}
		List<String> list = new ArrayList<>(set);
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return Integer.compare(o1.length(), o2.length());
			}
		});
		
		for (String string : list) {
			sb.append(string).append("\n");
		}
		System.out.println(sb);
	}

}
