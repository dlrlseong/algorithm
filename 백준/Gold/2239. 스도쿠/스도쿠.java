import java.io.*;
import java.util.*;

public class Main {
	static int[][] Map;
	static boolean[][] R, C, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Map = new int[1 + 9][1 + 9];
		R = new boolean[1 + 9][10];
		C = new boolean[1 + 9][10];
		S = new boolean[1 + 9][10];

		for (int i = 1; i <= 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 1; j <= 9; j++) {
				Map[i][j] = str.charAt(j - 1) - '0';
				int tmp = Map[i][j];
				if (tmp == 0)
					continue;
				R[i][tmp] = true;
				C[j][tmp] = true;
				S[findSection(i, j)][tmp] = true;
			}
		}
		dfs(1, 1);
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				sb.append(Map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static boolean dfs(int r, int c) {
		if (r == 10 && c == 1) {
			return true;
		}

		if (Map[r][c] != 0) {
			return dfs(r + (c / 9), (c % 9) + 1);
		}

		for (int i = 1; i <= 9; i++) {
			if (!R[r][i] && !C[c][i] && !S[findSection(r, c)][i]) {
				R[r][i] = true;
				C[c][i] = true;
				S[findSection(r, c)][i] = true;
				Map[r][c] = i;
				if (!dfs(r + (c / 9), (c % 9) + 1)) {
					R[r][i] = false;
					C[c][i] = false;
					S[findSection(r, c)][i] = false;
					Map[r][c] = 0;
				} else {
					return true;
				}

			}
		}
		// 넣을 수 있는게 없다.
		return false;
	}

	private static int findSection(int i, int j) {
		if (i <= 3) { // 1 2 3
			if (j <= 3) {
				return 1;
			} else if (j <= 6) {
				return 4;
			} else
				return 7;
		} else if (i <= 6) { // 4 5 6
			if (j <= 3) {
				return 2;
			} else if (j <= 6) {
				return 5;
			} else
				return 8;
		} else { // 7 8 9
			if (j <= 3) {
				return 3;
			} else if (j <= 6) {
				return 6;
			} else
				return 9;
		}
	}
}
