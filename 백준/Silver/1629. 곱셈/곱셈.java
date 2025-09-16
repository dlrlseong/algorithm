import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());

		System.out.println(power(A, B, C));
	}

	// 분할정복을 이용한 거듭제곱
	public static long power(long a, long b, long c) {
		// 기저 조건
		if (b == 0) {
			return 1;
		}

		// a를 c로 미리 나눠서 오버플로우 방지
		a %= c;

		// 분할정복
		long temp = power(a, b / 2, c);
		temp = (temp * temp) % c;

		// 지수가 홀수인 경우
		if (b % 2 == 1) {
			temp = (temp * a) % c;
		}

		return temp;
	}
}