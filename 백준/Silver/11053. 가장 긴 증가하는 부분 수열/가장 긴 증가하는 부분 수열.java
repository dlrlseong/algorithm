import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		list.add(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (list.get(list.size() - 1) < tmp) {
				list.add(tmp);
			} else {
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j) >= tmp) {
						list.remove(j);
						list.add(j, tmp);
						break;
					}
				}
			}
		}
//		System.out.println(list);
		System.out.println(list.size());

	}

}
