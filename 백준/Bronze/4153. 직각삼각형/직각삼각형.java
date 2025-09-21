import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			List<Integer> list = new ArrayList<>();
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				list.add(tmp);
				sum += tmp;
			}
			if (sum == 0)
				break;

			Collections.sort(list, Collections.reverseOrder());
			if ((list.get(0) * list.get(0)) == (list.get(1) * list.get(1) + list.get(2) * list.get(2))) {
				System.out.println("right");
			} else {
				System.out.println("wrong");
			}
		}
	}

}
