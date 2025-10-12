import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		while (list.size() > 1) {
			tmp = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (i % 2 == 1)
					tmp.add(list.get(i));
				if (i == list.size() - 1 && i % 2 == 0)
					tmp.add(0, list.get(i));
			}
			list = tmp;
		}

		System.out.println(list.remove(0));
	}
}