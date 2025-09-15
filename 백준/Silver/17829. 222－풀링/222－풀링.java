import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(Pooling222(0, 0, N));
	}

	private static int Pooling222(int si, int sj, int size) {
		int numbers[] = new int[4];
		int nsize = size / 2;
		if (nsize == 1) {
			numbers[0] = arr[si][sj];
			numbers[1] = arr[si][sj + nsize];
			numbers[2] = arr[si + nsize][sj];
			numbers[3] = arr[si + nsize][sj + nsize];
			Arrays.sort(numbers);
			return numbers[2];
		}
		numbers[0] = Pooling222(si, sj, nsize);
		numbers[1] = Pooling222(si, sj + nsize, nsize);
		numbers[2] = Pooling222(si + nsize, sj, nsize);
		numbers[3] = Pooling222(si + nsize, sj + nsize, nsize);
		Arrays.sort(numbers);
		return numbers[2];
	}
}
