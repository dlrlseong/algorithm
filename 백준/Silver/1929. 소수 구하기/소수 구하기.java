import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = M; i <= N; i++) {
			int tmp = make_prime(i);
			if(tmp>0) {
				sb.append(tmp).append("\n");
			}
		}
		System.out.println(sb);
	}

	public static int make_prime(int number) {

		// 0 과 1 은 소수가 아니므로 종료
		if (number < 2) {
			return -1;
		}
		// 2 는 소수다
		if (number == 2) {
			return number;
		}
		// 제곱근 함수 : Math.sqrt()
		for (int i = 2; i <= Math.sqrt(number); i++) {

			// 소수가 아닐경우 종료
			if (number % i == 0) {
				return -1;
			}
		}
		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
		return number;
	}
}