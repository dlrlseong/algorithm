import java.io.*;
import java.util.*;

public class Main {

	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		System.out.println((QuadTree(map, 0, 0, N)));

	}

	private static String QuadTree(int[][] map, int r, int c, int n) {
		String str = "";

		int sum = 0;
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				sum += map[i][j];
			}
		}

		if (sum == 0) {
			return "0";
		} else if (sum == n * n) {
			return "1";
		} else {
			str = "(";
			str += QuadTree(map, r, c, n / 2);
			str += QuadTree(map, r, c + n / 2, n / 2);
			str += QuadTree(map, r + n / 2, c, n / 2);
			str += QuadTree(map, r + n / 2, c + n / 2, n / 2);
			str += ")";
		}

		return str;
	}
}