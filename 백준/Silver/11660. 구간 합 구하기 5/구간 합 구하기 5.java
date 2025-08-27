import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N + 1];

		for (int i = 0; i < N; i++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				sum += Integer.parseInt(st.nextToken());
				arr[i][j] = sum;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken());
			int result = 0;
			for (int j = x1; j <= x2; j++) {
				result += arr[j][y2] - arr[j][y1 - 1];
			}
			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}
}
