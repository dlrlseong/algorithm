import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
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
					for (int i = 0; i < o1.length(); i++) {
						char c1 = o1.charAt(i);
						char c2 = o2.charAt(i);
						if (c1 == c2)
							continue;
						return Integer.compare(c1, c2);
					}
				}
				return Integer.compare(o1.length(), o2.length());
			}
		});
		
		for (String string : list) {
			System.out.println(string);
		}
	}

}
