import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		List<Integer> TshirtList = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 6; i++) {
			TshirtList.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int tcnt = 0;
		for (Integer num : TshirtList) {
			tcnt += num % T == 0 ? num / T : num / T + 1;
		}
		System.out.println(tcnt);
		System.out.println(N / P + " " + N % P);
	}
}
