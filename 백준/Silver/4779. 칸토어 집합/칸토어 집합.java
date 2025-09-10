import java.io.*;
import java.util.*;

public class Main {
	static boolean[] flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = "";
		while ((str = br.readLine())!=null) {
			if (str.length() == 0 || str == null)
				break;
			StringTokenizer st = new StringTokenizer(str, " ");
			int N = Integer.parseInt(st.nextToken());
			int NN = 1;
			for (int i = 0; i < N; i++) {
				NN *= 3;
			}
			flag = new boolean[1 + NN];
			f(1, NN);
			for (int i = 1; i <= NN; i++) {
				sb.append(flag[i] ? ' ' : '-');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void f(int s, int e) {
		int size = (e - s + 1) / 3;
		if (size == 0)
			return;

		for (int i = s + size; i <= e - size; i++) {
			flag[i] = true;
		}
		f(s, s + size - 1);
		f(e - size + 1, e);
	}

}
