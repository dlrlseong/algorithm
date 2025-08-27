import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		Deque<Integer> q = new ArrayDeque<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		char[] arr = st.nextToken().toCharArray();
		
		int max_size = N - K;

		for (Character c : arr) {
			int i = c - '0';
			if (q.size() == 0) {
				q.offerLast(i);
				continue;
			} else {

				while (K > 0) {
					if (q.size() == 0)
						break;
					else if (q.peekLast() < i) {
						q.pollLast();
						K--;
					} else {
						break;
					}
				}
				if (q.size() < max_size)
					q.offerLast(i);
			}

		}

		for (Integer i : q) {
			System.out.print(i);
		}

	}
}
