import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= 1000000; i++) {
			int tmp = i;
			int sum = tmp;

			while (tmp > 0) {
				sum += tmp % 10;
				tmp /= 10;
			}

			if (sum == N) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
}