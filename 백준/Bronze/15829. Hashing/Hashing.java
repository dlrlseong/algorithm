import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();

		int r = 31;
		int mod = 1234567891;

		long hashCode = 0;

		for (int i = 0; i < L; i++) {
			long num = str.charAt(i) - 'a' + 1;
			for (int j = 0; j < i; j++) {
				num = (num * r) % mod;
			}
			hashCode = (hashCode + num) % mod;
		}

		System.out.println(hashCode);
	}
}