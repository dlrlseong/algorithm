import java.io.*;
import java.util.*;

public class Main {
	static int N, K, Cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Cnt = 0;

		Comb(0, 1);
		System.out.println(Cnt);
	}

	private static void Comb(int cnt, int start) {

		if (cnt == K) {
			Cnt++;
			return;
		}

		for (int i = start; i <= N; i++) {
			Comb(cnt + 1, i + 1);
		}
	}
}