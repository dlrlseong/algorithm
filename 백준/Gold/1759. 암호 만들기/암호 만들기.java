import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[] arr = new char[C];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		String str = "";
		dfs(arr, str, 0, 0, 0);
		System.out.println(sb);
	}

	private static void dfs(char[] arr, String str, int start, int cnt1, int cnt2) {
		if (str.length() == L) {
			if (cnt1 < 1 || cnt2 < 2)
				return;
			sb.append(str).append("\n");
			return;
		}

		for (int i = start; i < C; i++) {
			if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				dfs(arr, str + arr[i], i + 1, cnt1 + 1, cnt2);
			} else {
				dfs(arr, str + arr[i], i + 1, cnt1, cnt2 + 1);
			}
		}
	}
}
