import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> primeNumbers = new HashSet<>();
		for (int i = 1; i < 1000000; i++) {
			if (isPrime(i))
				primeNumbers.add(i);
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			boolean flag = false;

			for (int i = 1; i <= N / 2; i++) {
				if (!primeNumbers.contains(i))
					continue;

				int j = N - i;
				if (!primeNumbers.contains(j))
					continue;

				flag = true;
				sb.append(N).append(" = ").append(i).append(" + ").append(j).append("\n");
				break;
			}
			if (!flag) {
				sb.append("Goldbach's conjecture is wrong.");
			}
		}
		System.out.println(sb);
	}

	public static boolean isPrime(int N) {
		// 2보다 작은 수는 소수가 아님
		if (N < 2) {
			return false;
		}

		// 2는 소수
		if (N == 2) {
			return true;
		}

		// 짝수는 소수가 아님 (2 제외)
		if (N % 2 == 0) {
			return false;
		}

		// 3부터 √N까지 홀수로만 나누어 확인
		for (int i = 3; i * i <= N; i += 2) {
			if (N % i == 0) {
				return false;
			}
		}

		return true;
	}
}
