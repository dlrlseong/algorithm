import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		int result = 0;
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			int input = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= input; j++) {
				if (input % j == 0)
					cnt++;
			}
			if (cnt == 2)
				result++;
		}
		System.out.println(result);
	}
}
