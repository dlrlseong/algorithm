import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		// 666 ~ 10000666
		int cnt = 0;
		for (int i = 666; i < 10000666; i++) {
			String str = Integer.toString(i);
			if (str.contains("666"))
				cnt++;
			if (cnt == N) {
				System.out.println(i);
				return;
			}
		}
	}
}