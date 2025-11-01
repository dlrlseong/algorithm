import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static int[] S, numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			if (k == 0) {
				System.out.println(sb);
				return;
			}

			S = new int[k];
			for (int i = 0; i < k; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			numbers = new int[6];
			comb(0, 0);
			sb.append("\n");
		}
	}

	private static void comb(int cnt, int start) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < k; i++) {
			numbers[cnt] = S[i];
			comb(cnt + 1, i + 1);
		}
	}
}
