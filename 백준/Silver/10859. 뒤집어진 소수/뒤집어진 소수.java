import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long N = Long.parseLong(st.nextToken());

		if (!isPrime(N)) {
			System.out.println("no");
			return;
		}

		long fliped_N = flip(N);
		if (fliped_N == -1) { // 뒤집을 수 없는 경우
			System.out.println("no");
			return;
		}

		if (!isPrime(fliped_N)) {
			System.out.println("no");
			return;
		}

		System.out.println("yes");
	}

	private static int convert(int n) {
		switch (n) {
		case 0:
		case 1:
		case 2:
		case 5:
		case 8:
			return n;
		case 6:
			return 9;
		case 9:
			return 6;
		case 3:
		case 4:
		case 7:
			return -1; // 뒤집을 수 없음
		}
		return -1;
	}

	private static long flip(long n) {
		long result = 0;
		while (n > 0) {
			int digit = convert((int) (n % 10));
			if (digit == -1) {
				return -1; // 뒤집을 수 없는 숫자 포함
			}
			result = result * 10 + digit;
			n = n / 10;
		}
		return result;
	}

	public static boolean isPrime(long N) {
		if (N < 2) {
			return false;
		}
		if (N == 2) {
			return true;
		}
		if (N % 2 == 0) {
			return false;
		}
		for (long i = 3L; i * i <= N; i += 2) {
			if (N % i == 0) {
				return false;
			}
		}
		return true;
	}
}