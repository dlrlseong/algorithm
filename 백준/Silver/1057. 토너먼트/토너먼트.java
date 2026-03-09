import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int cnt = 1;
		while (N >= 1) {
			for (int i = 1; i <= N; i += 2) {
//				System.out.println(i + " " + (i + 1));
				if (A == B || i == A && i + 1 == B || i == B && i + 1 == A) {
					System.out.println(cnt);
					return;
				}
			}
			cnt++;
			A = (int) Math.round((A + 0.5) / 2);
			B = (int) Math.round((B + 0.5) / 2);

//			System.out.println(A + " " + B);
			N = N / 2;
		}
	}

}
