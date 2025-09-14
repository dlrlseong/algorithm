import java.io.*;
import java.util.*;

/*
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			f(n);
		}
	}

	private static void f(int n) {
		int dpZero[] = new int[1 + 40];
		int dpOne[] = new int[1 + 40];
		dpZero[0] = 1;
		dpOne[1] = 1;
		for (int i = 2; i <= n; i++) {
			dpZero[i] = dpZero[i - 1] + dpZero[i - 2];
			dpOne[i] = dpOne[i - 1] + dpOne[i - 2];
		}

		System.out.println(dpZero[n] + " " + dpOne[n]);
	}
}
