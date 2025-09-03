import java.io.*;
import java.util.*;

public class Main {
	static int[][] Memory;
	static int[][] Cost;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		Cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				Cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Memory = new int[N][3];
		Memory[0][0] = Cost[0][0];
		Memory[0][1] = Cost[0][1];
		Memory[0][2] = Cost[0][2];
		for (int i = 1; i < N; i++) {
			Memory[i][0] = Cost[i][0] + Math.min(Memory[i - 1][1], Memory[i - 1][2]);
			Memory[i][1] = Cost[i][1] + Math.min(Memory[i - 1][0], Memory[i - 1][2]);
			Memory[i][2] = Cost[i][2] + Math.min(Memory[i - 1][0], Memory[i - 1][1]);
		}
		int min = Integer.MAX_VALUE;
		for (int n : Memory[N - 1]) {
			min = Math.min(min, n);
		}
		System.out.println(min);
	}
}
