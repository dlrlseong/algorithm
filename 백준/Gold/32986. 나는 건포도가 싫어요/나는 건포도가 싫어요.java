import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		if (A == 3 && B == 3 && C == 3) {
			System.out.println(0);
			return;
		}
		int min = Math.min(Math.min(A, B), C);
		int cnt = f(min);
		System.out.println(cnt);
	}

	private static int f(int x) {
		if (x <= 2)
			return 0;
		return 1 + f(x - 2) + f(2);
	}
}
