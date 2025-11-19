import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int cntArr[] = new int[7];
		int MOD = 1000000007;
		int sum = 0;

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = (int) (Long.parseLong(st.nextToken()) % MOD);
			cntArr[A[i] % 7]++;
		}
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			B[i] = (int) (Long.parseLong(st.nextToken()) % MOD);
			sum += B[i];
		}

		int cntDeleted = 0;
		boolean isDeleted[] = new boolean[7];
		long sumArr[] = new long[7];
		for (int i = 0; i < 7; i++) {
			sumArr[i] = i;
		}
		for (int i = 0; i < M; i++) {
			long backup[] = new long[7];
			System.arraycopy(sumArr, 0, backup, 0, 7);
			for (int j = 0; j < 7; j++) {
				if (isDeleted[j])
					continue;
				sumArr[j] = (sumArr[j] + B[i]);
				if (sumArr[j] % 7 == 0) {
					if (cntDeleted + cntArr[j] == N) {
						sumArr[j] = backup[j];
						continue;
					}
					isDeleted[j] = true;
					cntDeleted += cntArr[j];
				}
			}
		}

		System.out.println(N - cntDeleted);
		for (int i = 0; i < N; i++) {
			if (!isDeleted[A[i] % 7]) {
				System.out.print((A[i] + sumArr[A[i] % 7] - A[i] % 7) % MOD + " ");
			}
		}

	}
}
