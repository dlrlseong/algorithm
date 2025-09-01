import java.util.*;
import java.io.*;

public class Solution {
	static Set<String> set;
	static char[][] Map;
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			Map = new char[4][4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					Map[i][j] = st.nextToken().charAt(0);
				}
			}
			set = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					String str = "";
					str += Map[i][j];
					dfs(1, i, j, str);
				}
			}
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		System.out.println(sb);
		
	}

	private static void dfs(int cnt, int i, int j, String str) {
		if (cnt == 7) {
			set.add(str);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4)
				continue;
			dfs(cnt + 1, nr, nc, str + Map[nr][nc]);
		}
	}

}
