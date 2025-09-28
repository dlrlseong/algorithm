import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String str = st.nextToken();

		int m = str.charAt(12) - '0';
		int value = 0;

		int starIdx = -1;

		for (int i = 0; i < 12; i++) {
			if (str.charAt(i) == '*') {
				starIdx = i;
				continue;
			}

			if (i % 2 == 1) {
				value += (str.charAt(i) - '0') * 3;
			} else {
				value += (str.charAt(i) - '0');
			}
		}

		value = value % 10;

		for (int i = 0; i <= 9; i++) {
			int num = i;
			if (starIdx % 2 == 1) {
				num = num * 3;
			}

			if (m == (10 - ((value + num) % 10)) % 10) {
				System.out.println(i);
				return;
			}
		}
//		System.out.println(0);

	}
}
