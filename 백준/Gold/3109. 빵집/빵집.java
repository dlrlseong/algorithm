import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] Map;
	static final int[] dr = { -1, 0, 1 };
	static final int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Map = new char[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 0; j < C; j++) {
				Map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			Pipe(i, 0);
		}

		int cnt = 0;
		for (int i = 0; i < R; i++) {
			if (Map[i][C - 1] == '.' || Map[i][C - 1] == 'x')
				continue;
			cnt++;
		}
//		for (char[] a : Map)
//			System.out.println(Arrays.toString(a));
		System.out.println(cnt);
	}

	private static boolean Pipe(int r, int c) {
		Map[r][c] = '-';
		boolean isConnected = false;
		if (c == C - 1)
			return isConnected = true;
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= R || nc >= C)
				continue;
			if (Map[nr][nc] == 'x' || Map[nr][nc] == '-')
				continue;
			isConnected = Pipe(nr, nc);
			if (isConnected)
				break;
		}
		return isConnected;
	}
}
