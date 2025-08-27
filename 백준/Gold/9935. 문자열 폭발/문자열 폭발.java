import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		char[] boom = br.readLine().toCharArray();

		Deque<Character> s = new ArrayDeque<>();
		Deque<Character> tmps = new ArrayDeque<>();

		for (Character c : input) {
			s.offerLast(c);

			if (s.size() < boom.length)
				continue;
			if (s.peekLast() != null && s.peekLast() == boom[boom.length - 1]) {
				int flag = 1;
				for (int i = boom.length - 1; i >= 0; i--) {
					tmps.offerLast(s.pollLast());
					if (tmps.peekLast() != boom[i]) {
						flag = 0;
						break;
					}
				}

				if (flag == 1) {
					tmps.clear();
				} else {
					while (tmps.size() > 0)
						s.offerLast(tmps.pollLast());
				}
			}
		}

		if (s.size() == 0) {
			System.out.println("FRULA");
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (Character c : s) {
			sb.append(c);
		}
		System.out.println(sb);

	}

}
