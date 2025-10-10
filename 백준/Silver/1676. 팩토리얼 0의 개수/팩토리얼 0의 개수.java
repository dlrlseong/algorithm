import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int cnt2 = 0;
		int cnt5 = 0;
		for (int i = 1; i <= N; i++) {
			int tmp = i;
			while(tmp%2==0 || tmp%5==0) {
				if(tmp%2==0) {
					cnt2++;
					tmp/=2;
				}
				if(tmp%5==0) {
					cnt5++;
					tmp/=5;
				}
			}
		}
		System.out.println(Math.min(cnt2, cnt5));
	}
}
