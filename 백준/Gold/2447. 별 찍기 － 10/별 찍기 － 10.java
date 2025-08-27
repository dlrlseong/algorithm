import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = N / 3;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(check(i, j));
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static char check(int i, int j) {
		if (i == 1 && j == 1)
			return '*';

		if (i % 3 == 2 && j % 3 == 2) {
			return ' ';
		} else
			return check((i+2) / 3, (j+2) / 3);

	}

}
