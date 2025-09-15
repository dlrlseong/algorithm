import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[][];
	static int cnt[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		cnt = new int[3]; // 0: -1 | 1: 0 | 2: 1
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		countNumOfPaper(0, 0, N);
		for (int a : cnt) {
			System.out.println(a);
		}
	}

	private static void countNumOfPaper(int r, int c, int size) {
		if (size == 0)
			return;
		int sum = 0;
		int tmp = arr[r][c];
		A: for (int row = r; row < r + size; row++) {
			for (int col = c; col < c + size; col++) {
				sum += arr[row][col];
				if (arr[row][col] != tmp) {
					sum = Integer.MAX_VALUE;
					break A;
				}
			}
		}
		if (sum == size * size * -1) {
			cnt[0]++;
		} else if (sum == size * size) {
			cnt[2]++;
		} else if (sum == 0) {
			cnt[1]++;
		} else {
			int nsize = size / 3;
			countNumOfPaper(r, c, nsize);
			countNumOfPaper(r, c + nsize, nsize);
			countNumOfPaper(r, c + nsize + nsize, nsize);
			countNumOfPaper(r + nsize, c, nsize);
			countNumOfPaper(r + nsize, c + nsize, nsize);
			countNumOfPaper(r + nsize, c + nsize + nsize, nsize);
			countNumOfPaper(r + nsize + nsize, c, nsize);
			countNumOfPaper(r + nsize + nsize, c + nsize, nsize);
			countNumOfPaper(r + nsize + nsize, c + nsize + nsize, nsize);
		}
	}
}
